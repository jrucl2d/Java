package com.example.reactorcore;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

import java.time.Duration;

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

    @Test
    void name3() {
        // verify의 리턴 값은 Duration
        Duration duration = StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(1)))
            .expectSubscription() // expectNoEvent는 subscription도 하나의 이벤트로 간주함.
            .expectNoEvent(Duration.ofDays(1)) // 하루동안 아무 일도 일어나지 않기를 기대함
            .expectNext(0L) // 지연 이후 0을 방출하길 기대함
            .verifyComplete();// 완료 기대 후 검증 트리거

        System.out.println(duration);
    }

    @Test
    void name4() {
        StepVerifier.create(Mono.just(1).map(i -> i + 10),
            StepVerifierOptions.create().withInitialContext(Context.of("thing1", "thing2")))
            .expectAccessibleContext()
            .contains("thing1", "thing2") // context expactation
            .then() // 데이터를 검증하는 일반 expectation으로 돌아간다.
            .expectNext(11)
            .verifyComplete();
    }

    private <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }
}
