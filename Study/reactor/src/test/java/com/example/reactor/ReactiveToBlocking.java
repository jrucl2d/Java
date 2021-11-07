package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveToBlocking {
    @Test
    void name() {
        Mono.just(1)
                .block(); // block Mono
    }

    @Test
    void name1() {
        Iterable<Integer> integers = Flux.range(1, 19)
                .toIterable();

        integers.forEach(System.out::println);
    }
}
