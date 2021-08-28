package com.example.completablefuture.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class TaskConfig
{
    public static ThreadPoolTaskExecutor executor;

    static
    {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("Executor-");
        executor.initialize();
    }
}
