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
        CompletableFuture.supplyAsync(() -> {
                    log.info("supplyAsunc");
//                    if (1 == 1) throw new RuntimeException();
                    return 1;
                })
                .thenApply(s -> { // map과 유사
                    log.info("thenApply");
                    return s + 1;
                })
                .thenCompose(s -> { // flatMap과 유사
                    log.info("thenCompose");
                    return CompletableFuture.completedFuture(s);
                })
                .exceptionally(e -> -10) // 예외를 복구한다.
                .thenAccept(s -> log.info("thenAccept " + s));
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
