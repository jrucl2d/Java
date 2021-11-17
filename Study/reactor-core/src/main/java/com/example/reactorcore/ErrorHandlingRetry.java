package com.example.reactorcore;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.util.concurrent.atomic.AtomicInteger;

public class ErrorHandlingRetry {
    public static void main(String[] args) {
        Flux.error(new IllegalArgumentException())
            .doOnError(System.out::println)
            .retryWhen(Retry.from(companion -> companion.take(3))) // retry(3)과 비슷한
            .subscribe(System.out::println);

        AtomicInteger errorCount = new AtomicInteger();
        var flux = Flux.error(new IllegalArgumentException())
            .doOnError(e -> errorCount.incrementAndGet())
            .retryWhen(Retry.from(companion -> companion.map(rs -> {
                if (rs.totalRetries() < 3) return rs.totalRetries();
                else throw Exceptions.propagate(rs.failure()); // 시퀀스를 에러로 끝내기 위해 세 번 재시도 후 기존 예외를 던진다.
            }
            )));
        flux.subscribe(System.out::println);
    }
}
