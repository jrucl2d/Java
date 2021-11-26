package com.example.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Slf4j
@EnableAsync // Async 사용하도록
@SpringBootApplication
public class ReactiveApplication {
    @RestController
    public static class MyController {
        Queue<DeferredResult> results = new ConcurrentLinkedDeque<>();

        @GetMapping("/")
        public DeferredResult<String> dr() {
            log.info("dr");
            DeferredResult<String> dr = new DeferredResult<>(600000L);
            results.add(dr);
            return dr;
        }

        @GetMapping("/dr/count")
        public String drCount() {
            return "" + results.size();
        }

        @GetMapping("/dr/event")
        public String drevent(String message) {
            for (DeferredResult result : results) {
                result.setResult("Hello" + message);
                results.remove(result);
            }
            return "OK";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }
}
