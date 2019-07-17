package com.company.common.config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**异步请求*/
@Configuration
public class SpringAsyncConfig {
	private int corePoolSize=10;
	private int maximumPoolSize=20;
	private int keepAliveTime=30;
	private int queueCapacity=20;
	
	private ThreadFactory threadFactory=
	new ThreadFactory() {
		
		private AtomicLong number=new AtomicLong(100);
		
		@Override
		public Thread newThread(Runnable r) {
			return new Thread(
			r,"async-thread-"+number.getAndIncrement());
		}
		};
		
		@Bean("asyncThreadPool")
		public ThreadPoolExecutor newThreadPoolExecutor() {
			ThreadPoolExecutor pool =
			new  ThreadPoolExecutor(
			corePoolSize,
			maximumPoolSize,
			keepAliveTime,
			TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(queueCapacity),
			threadFactory);
			
			return pool;
		}
		
	}
