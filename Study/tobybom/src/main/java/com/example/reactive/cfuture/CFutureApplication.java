package com.example.reactive.cfuture;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

public class CFutureApplication {
    // ListenableFuture를 CompletableFuture로 변경해주는 코드
    <T> CompletableFuture<T> toCF(ListenableFuture<T> lf) {
        CompletableFuture<T> cf = new CompletableFuture<>();
        lf.addCallback(s -> {cf.complete(s);}, e -> cf.completeExceptionally(e));
        return cf;
    }
    public static void main(String[] args) {

    }
}
