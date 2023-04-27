package com.springboot.uber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.springboot.uber.config.AsyncConfiguration;

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

		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) asyncConfiguration.getAsyncExecutor(); // get the
																												// taskExecutor
																												// bean
																												// from
		// asyncConfiguration
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
