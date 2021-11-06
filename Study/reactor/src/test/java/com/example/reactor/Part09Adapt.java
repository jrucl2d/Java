package com.example.reactor;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Part09Adapt {
    @Test
    void name() {
        // Flowable <-> Flux
        Flowable<Integer> flowable = Flowable.fromPublisher(Flux.just(2, 3));
        flowable.subscribe(System.out::println);

        Flux.from(flowable)
                .subscribe(System.out::println);
    }

    @Test
    void name1() {
        // Observable은 Publisher를 구현하지 않았으므로 Flux.from 사용 불가. Flowable로 변환 후 사용
        Flowable<Integer> flowable = Observable.just(1, 2, 3).toFlowable(BackpressureStrategy.BUFFER);
        flowable.subscribe(System.out::println);

        Flux.from(flowable)
                .subscribe(System.out::println);
    }

    @Test
    void name2() {
        Mono<Integer> mono = Mono.just(1);
        Single<Integer> single = Single.just(2);

        Mono.from(single.toFlowable())
                .subscribe(System.out::println);

        Single.fromPublisher(mono)
                .subscribe(System.out::println);
    }

    @Test
    void name3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        future.thenApply(String::toUpperCase);
//                .get();

        // CompletableFuture -> Flux
        Mono.fromFuture(future)
                .subscribe(System.out::println);
    }
}
