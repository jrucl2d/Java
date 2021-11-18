package com.example.reactorcore;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.publisher.PublisherProbe;
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

    @Test
    void name5() {
        StepVerifier.create(processOrFallback(Mono.just("just a phrase with    tabs!"),
            Mono.just("Empty_PHRASE")))
            .expectNext("just", "a", "phrase", "with", "tabs!")
            .verifyComplete();
    }

    @Test
    void name6() {
        StepVerifier.create(processOrFallback(Mono.empty(),
                Mono.just("Empty_PHRASE")))
            .expectNext("Empty_PHRASE")
            .verifyComplete();
    }

    private Flux<String> processOrFallback(Mono<String> source, Mono<String> fallBack)
    {
        return source
            .flatMapMany(ph -> Flux.fromArray(ph.split("\\s+")))
            .switchIfEmpty(fallBack);
    }

    private <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }

    private Mono<String> executeCommand(String command) {
        return Mono.just(command + " DONE");
    }

    public Mono<Void> processOrFallback2(Mono<String> commandSource, Mono<Void> doWhenEmpty) {
        return commandSource
            .flatMap(command -> executeCommand(command).then())
            .switchIfEmpty(doWhenEmpty);
    }

    @Test
    void name7() {
        PublisherProbe<Void> probe = PublisherProbe.empty(); // 빈 시퀀스로 전환되는 프로브 생성

        StepVerifier.create(processOrFallback2(Mono.empty(), probe.mono())) // Mono<Void> 자리에 프로브 사용
            .verifyComplete();

        probe.assertWasSubscribed(); // 시퀀스 완료 후 프로브로 사용 여부 확인 가능
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }
}
