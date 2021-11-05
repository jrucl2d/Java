package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class Part07Errors {

    @Test
    void name() {
        Mono<Object> mono = Mono.error(new RuntimeException());
        mono.log().onErrorReturn(2) // 에러 발생시 2를 리턴
            .doOnNext(System.out::println)
            .subscribe();
    }

    @Test
    void name1() {
        Mono<Object> mono = Mono.error(new RuntimeException());
        mono.log().onErrorResume(e -> Mono.just(2)) // 에러 발생시 에러를 받아서 Mono를 리턴(stream을 넘겨줌)
            .doOnNext(System.out::println) // 결과 위와 같은 2가 리턴됨
            .subscribe();
    }
}
