package com.springboot.uber.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Async("myThreadPoolTaskExecutor")
	public CompletableFuture<String> doSomethingAsync() {
		logger.info("Starting async method");
		try {
			// Simulate a long-running task
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("Async method interrupted", e);
		}
		logger.info("Async method completed");
		return CompletableFuture.completedFuture("Async method result");
	}
}
