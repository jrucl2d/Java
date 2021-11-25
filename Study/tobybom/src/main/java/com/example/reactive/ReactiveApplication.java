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
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Slf4j
@EnableAsync // Async 사용하도록
@SpringBootApplication
public class ReactiveApplication {

    @Component
    public static class MyService {
        @Async // AOP로 Async하게 실행
        public Future<String> hello() throws InterruptedException {
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
            final Future<String> future = myService.hello();
            log.info("exit " + future.isDone());
            log.info("result " + future.get());
        };
    }
}
