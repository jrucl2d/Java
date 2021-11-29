package com.example.reactive.cfuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CfutureApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        completableFutureBasic();
        CompletableFuture.runAsync(() -> log.info("runAsync"))
                .thenRun(() -> log.info("thenRun")); // 비동기 완료 이후 실행됨
        log.info("exit");
        ForkJoinPool.commonPool().shutdown();
        ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
    }

    private static void completableFutureBasic() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> f = CompletableFuture.completedFuture(1);
        System.out.println(f.get());

        CompletableFuture<Integer> f2 = new CompletableFuture<>(); // 객체 만들기만 함
        f2.complete(2); // 다른 곳에서 완료 시킴
//        f2.completeExceptionally(new RuntimeException());
        System.out.println(f2.get());
    }
}
