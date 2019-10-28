package com.menu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//开启异步
@EnableAsync
public class ThreadPoolTaskConfig {

    /**
     * 此线程池用于异步相关操作
     *
     * @return
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程
        executor.setCorePoolSize(10);
        //最大线程
        executor.setMaxPoolSize(100);
        //缓冲队列
        executor.setQueueCapacity(200);
        //允许线程最大空闲数  /s
        executor.setKeepAliveSeconds(10);
        //线程池前缀
        executor.setThreadNamePrefix("Async_Thread");
        //线程对拒绝任务的处理策略  不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
