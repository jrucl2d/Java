package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Part06Request {

    @Test
    void name() {
        Flux<Long> flux = Flux.interval(Duration.ofMillis(10));
        StepVerifier.create(flux)
                .expectNextCount(4)
                .expectComplete();
    }

    @Test
    void name1() {
        Flux<Part03StepVerifierTest.User> flux = Flux.just(Part03StepVerifierTest.User.SKYLER, Part03StepVerifierTest.User.JESSE);
        StepVerifier.create(flux, 1) // request 1
                .expectNext(Part03StepVerifierTest.User.SKYLER)
                .thenRequest(1)
                .expectNext(Part03StepVerifierTest.User.JESSE)
                .thenCancel()
                .verify();
    }

    @Test
    void myTest() {
        Flux<Integer> flux = Flux.just(0, 1, 2);
        StepVerifier.create(flux, 1)
                .expectNext(0)
                .thenRequest(1)
                .expectNext(1)
                .thenRequest(1)
                .expectNext(2)
                .expectComplete()
                .verify();
    }
}
