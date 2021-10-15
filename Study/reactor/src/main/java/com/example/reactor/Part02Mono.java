package com.example.reactor;

import reactor.core.publisher.Mono;

import java.time.Duration;

// n 개의 이벤트를 만드는 Flux 와 달리 1개만 만들 수 있는 Mono
public class Part02Mono {

    // empty Mono
    Mono<String> emptyMono() {
        return Mono.empty();
    }

    // emit 아예 안 하는 Mono. onComplete 조차 안 함.
    Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

    // error 생성
    Mono<String> errorMono() {
        return Mono.error(new IllegalStateException());
    }

    // Flux 처럼 Mono 에서도 unique 값을 만들 수 있다.
    Mono<String> fooMono() {
        return Mono.just("foo");
    }

    public static void main(String[] args) {
        // or 사용시 현재의 Mono 혹은 파라미터로 넘긴 Mono 중 하나가 리턴됨.
        // or 사용시 제네릭 타입이 같아야 함.

        Mono<Long> delay = Mono.delay(Duration.ofMillis(100));
        Mono<Long> just = Mono.just(3L);
        Mono.just(1L)
                .map(llong -> llong * 2)
                .or(just)
                .subscribe(System.out::println);
    }
}
