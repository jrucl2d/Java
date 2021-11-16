package com.example.reactorcore;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class ThreadingAndSchedulers {
    public static void main(String[] args) throws InterruptedException
    {
//        mono();
//        flux();
//        publishOn();
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        final var flux = Flux.range(1, 2)
            .map(i -> 10 + i)
            .subscribeOn(s)
            .map(i -> "value " + i);

        new Thread(() -> flux.subscribe(System.out::println));
    }

    private static void publishOn() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final var flux = Flux.range(1, 2)
            .map(i -> 10 + i)
            .publishOn(s)
            .map(i -> "value " + i);

        new Thread(() -> flux.subscribe(System.out::println));
    }

    private static void flux() {
        Flux.interval(Duration.ofMillis(300), Schedulers.newSingle("test"))
            .take(10)
            .subscribe(v -> System.out.println(v + " thread : " + Thread.currentThread().getName()));
    }

    private static void mono() throws InterruptedException {
        final var mono = Mono.just("hello "); // Mono 데이터 수집

        Thread t = new Thread(() -> mono
            .map(msg -> msg + "thread ") // 구독은 Thread-0에서 진행
            .subscribe(v -> System.out.println(v + Thread.currentThread().getName())));
        t.start();
        t.join();
    }
}
