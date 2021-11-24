package com.example.reactive.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureEx {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();

        // 오래 걸리는 작업
        Future<String> f = es.submit(() -> {
            Thread.sleep(2000);
            log.info("Async");
            return "Hello";
        });
        System.out.println(f.isDone());
        log.info("Something else"); // 병렬적으로 Async와 같이 돌아감
        System.out.println(f.get()); // 결과를 받을 때까지 blocking 되어있다.
        System.out.println(f.isDone());
    }
}
