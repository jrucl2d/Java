package com.example.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

// Flux -> Publisher
public class Part01Flux {
    // 빈 Flux 생성
    Flux<String> emptyFlux() {
        return Flux.empty();
    }

    // 특정 아이템을 emit 하고 완료되는 Flux 생성
    Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

    // Flux 에 publish 할 item 의 list 만들기
    Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar"));
    }

    // 에러 Flux 를 생성할 수 있다. 이는 Flux 가 생성하는 마지막 event 가 된다.
    Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

    // interval 은 무한개의 스트림에 해당하는 Flux 를 생성. 여기서 take 로 개수만큼만 사용
    Flux<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(10);
    }

    public static void main(String[] args) throws InterruptedException {
        Flux.error(new IllegalStateException("error!"))
                .doOnError(System.out::println)
                .subscribe(); // subscribe 를 해야 에러 리턴하는 Flux 가 동작.

        Flux.interval(Duration.ofMillis(100))
                .take(10)
                .subscribe(System.out::println);
        Thread.sleep(2000); // thread 를 멈춰줘야 결과가 찍한다. (asynchronous)
    }
}
