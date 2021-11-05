package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

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

    @Test
    void name2() {
        // Flux도 Mono와 같음.
        Flux<Object> flux = Flux.error(new RuntimeException());
        flux.log().onErrorResume(e -> Flux.just(2, 3))
            .doOnNext(System.out::println)
            .subscribe();
    }

    @Test
    void name3() {
        // Checked Exception은 try catch로 잡아서 RuntimeException으로 감싸서 리턴해야 한다.
        Mono.just("hello")
            .log()
            .map(s -> {
                try {
                    return Integer.parseInt(s);
                } catch (Exception e) {
                    throw Exceptions.propagate(e); // runtime exception으로 바꿔줌
                    // StepVerifier나 Subscriber가 까서 볼 수 있음. 그 때 CheckedException으로 보임
                }
            })
            .onErrorReturn(1243)
            .doOnNext(System.out::println)
            .subscribe();
    }

    private static class MyException extends Exception {}
}
