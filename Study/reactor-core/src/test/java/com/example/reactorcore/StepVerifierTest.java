package com.example.reactorcore;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class StepVerifierTest
{
    @Test
    void name() {
        var source = Flux.just("thing1", "thing2");

        StepVerifier.create(
                appendBoomError(source)
        )
            .expectNext("thing1")
            .expectNext("thing2")
            .expectErrorMessage("boom") // 마지막에 onError와 함께 시퀀스 종료되길 바람.
            .verify(); // verify로 테스트 트리거 해야 함.
    }

    @Test
    void name1() {
        StepVerifierOptions.create()
            .scenarioName("name"); // 전체 시나리오 이름 지정 가능. assertion 에러 메시지에 사용됨.
    }

    private <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }
}
