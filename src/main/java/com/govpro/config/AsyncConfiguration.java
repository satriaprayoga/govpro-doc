package com.govpro.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.govpro.config.async.ExceptionHandlingAsyncTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer {
	
	private final Logger logger=LoggerFactory.getLogger(AsyncConfiguration.class);
	
	private final GovproProperties govproProperties;
	
	public AsyncConfiguration(GovproProperties govproProperties){
		this.govproProperties=govproProperties;
	}

	@Override
	@Bean(name="taskExecutor")
	public Executor getAsyncExecutor() {
		logger.debug("Creating Asynchronous Task Executor");
		ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(govproProperties.getAsync().getCorePoolSize());
		executor.setMaxPoolSize(govproProperties.getAsync().getMaxPoolSize());
		executor.setQueueCapacity(govproProperties.getAsync().getQueueCapacity());
		executor.setThreadNamePrefix("govpro-app-executor");
		return new ExceptionHandlingAsyncTaskExecutor(executor);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}

}
