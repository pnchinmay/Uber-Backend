package com.springboot.uber.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.uber.config.AsyncConfiguration;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class MyController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private myThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private AsyncConfiguration asyncConfiguration;

	@PostMapping("/process")
	public DeferredResult<ResponseEntity<String>> processRequest(@RequestParam(required = false) String param1,
			@RequestParam(required = false) String param2, @RequestParam(required = false) String param3) {

		// a list contains N objects ( unknown number of strings)
		// call an external api
		// this api takes these N strings and processes requests based on the parameters
		// count
		// this processing is done using ThreadPoolExecutor in an async way

		int numParams = countParams(param1, param2, param3);

		logger.info("Received request with {} parameter(s)", numParams);
		DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();

		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) asyncConfiguration.getAsyncExecutor();
		// get the taskExecutor bean from asyncConfiguration
		taskExecutor.execute(() -> {
			String response;
			if (numParams == 0) {
				response = "Please provide at least one parameter";
			} else if (numParams == 1) {
				response = "Processed param: " + param1;
			} else if (numParams == 2) {
				response = "Processed params: " + param1 + ", " + param2;
			} else {
				response = "Processed params: " + param1 + ", " + param2 + ", " + param3;
			}
			logger.info("Returning response: {}", response);
			deferredResult.setResult(ResponseEntity.ok(response));
		});

		return deferredResult;
	}

	@PostMapping("/print-numbers")
	public DeferredResult<ResponseEntity<Void>> printNumbers(
			@org.springframework.web.bind.annotation.RequestBody ArrayList<Integer> numbers) {
		logger.error("Received request to print {} numbers asynchronously", numbers.size());

		DeferredResult<ResponseEntity<Void>> deferredResult = new DeferredResult<>();
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) asyncConfiguration.getAsyncExecutor();

		taskExecutor.execute(() -> {
			logger.info("Printing numbers asynchronously:");
			for (Integer number : numbers) {
				logger.info(String.valueOf(number));
			}
			logger.info("Done printing numbers");
			deferredResult.setResult(ResponseEntity.ok().build());
		});

//		for (Integer number : numbers) {
//			taskExecutor.execute(() -> {
//				logger.info("Printing number {} in a separate thread", number);
////				try {
//////					Thread.sleep(5000); // wait for 1 second
////				} catch (InterruptedException e) {
////					e.printStackTrace();
////				}
//				logger.info(String.valueOf(number));
//			});
//		}

		taskExecutor.execute(() -> {
			logger.info("Done printing all numbers");
			deferredResult.setResult(ResponseEntity.ok().build());
		});

		return deferredResult;
	}

	@PostMapping("/scoring")
	public DeferredResult<ResponseEntity<Void>> scoring(
			@org.springframework.web.bind.annotation.RequestBody ArrayList<Integer> tcins) {
		// Read the value of x from the file
		DeferredResult<ResponseEntity<Void>> deferredResult = new DeferredResult<>();
		int x = 0;
		System.out.println(x);
		System.out.println(tcins);
		try {
			BufferedReader reader = new BufferedReader(new FileReader("a.txt"));
			String line = reader.readLine();
			x = Integer.parseInt(line);
			System.out.println(x);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Divide the tcins list into smaller lists of size x
//		List<List<Integer>> smallerLists = new ArrayList()<>();
//		List<List<Integer>> smallerLists = new ArrayList<>();
		ArrayList<ArrayList<Integer>> smallerLists = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		for (int i = 0; i < tcins.size(); i += x) {
			int end = Math.min(i + x, tcins.size());
			ArrayList<Integer> sublist = new ArrayList<>(tcins.subList(i, end));
			System.out.println(sublist);
			smallerLists.add(sublist);
		}

		// Send each smaller list to /print-numbers asynchronously
		/*
		 * // ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor)
		 * asyncConfiguration.getAsyncExecutor(); // for (List<Integer> smallerList :
		 * smallerLists) { // taskExecutor.execute(() -> { // logger.
		 * info("Sending a smaller list of size {} to /print-numbers asynchronously",
		 * smallerList.size()); // ResponseEntity<Void> response =
		 * restTemplate.postForEntity("/print-numbers", smallerList, Void.class); //
		 * logger.info("Received response from /print-numbers endpoint: {}",
		 * response.getStatusCode()); // }); // }
		 */ ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) asyncConfiguration.getAsyncExecutor();
		for (List<Integer> smallerList : smallerLists) {
			taskExecutor.execute(() -> {
				logger.info("Sending a smaller list of size {} to /print-numbers asynchronously", smallerList.size());

				OkHttpClient client = new OkHttpClient();
//                MediaType mediaType = MediaType.parse("application/json");
//                RequestBody body = RequestBody.create(mediaType, new Gson().toJson(smallerList));
				okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json; charset=utf-8");
				String jsonBody = "[" + smallerList.stream().map(Object::toString).collect(Collectors.joining(","))
						+ "]";
				RequestBody body = RequestBody.create(mediaType, jsonBody);

				Request request = new Request.Builder().url("http://localhost:8080/print-numbers").post(body).build();
				try {
					Response response = client.newCall(request).execute();
					logger.info("Received response from /print-numbers endpoint: {}", response.code());
					deferredResult.setResult(ResponseEntity.ok().build());
				} catch (IOException e) {
					logger.error("Error sending request to /print-numbers endpoint", e);
					deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
				}
			});
		}
		return deferredResult;
	}

	private int countParams(String... params) {
		int count = 0;
		for (String param : params) {
			if (param != null) {
				count++;
			}
		}
		return count;
	}

}
