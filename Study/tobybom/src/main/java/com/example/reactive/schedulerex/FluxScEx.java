package com.example.reactive.schedulerex;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxScEx {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .publishOn(Schedulers.newSingle("pub")) // consumer가 느린경우에
                .log()
//                .subscribeOn(Schedulers.newSingle("sub")) // subscribe 시점에 새로운 스레드
                .subscribe(System.out::println);
        System.out.println("exit");

        // 자동으로 timer-1이라는 스레드에서 실행된다(데몬 스레드).
        Flux.interval(Duration.ofMillis(500))
                .take(5)
                .subscribe(System.out::println);
    }
}
