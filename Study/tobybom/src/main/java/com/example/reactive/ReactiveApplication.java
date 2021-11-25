package com.example.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@EnableAsync // Async 사용하도록
@SpringBootApplication
public class ReactiveApplication {
    // 직접 ThreadPool을 설정
    @Bean
    ThreadPoolTaskExecutor tp() {
        ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
        te.setCorePoolSize(10);
        te.setMaxPoolSize(100); // 큐가 꽉 차야지 MAX 내에서 쓰레드 하나가 추가된다.
        te.setQueueCapacity(200);
        te.setThreadNamePrefix("myThread-");
        te.initialize();
        return te;
    }

    @Component
    public static class MyService {
        @Async(value = "tp") // AOP로 Async하게 실행, ThreadPool 이름을 지정
        public ListenableFuture<String> hello() throws InterruptedException {
            log.info("hello()");
            Thread.sleep(1000);
            return new AsyncResult<>("Hello");
        }
    }

    public static void main(String[] args) {
        try(ConfigurableApplicationContext c = SpringApplication.run(ReactiveApplication.class, args)) {

        }
    }

    @Autowired
    private MyService myService;

    @Bean
    ApplicationRunner run() {
        return args -> {
            log.info("run()");
            ListenableFuture<String> future = myService.hello();
            future.addCallback(System.out::println, System.err::println);
            log.info("exit");
        };
    }
}
