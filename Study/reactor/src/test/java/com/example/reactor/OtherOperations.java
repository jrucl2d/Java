package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OtherOperations {
    @Test
    void name() {
        var f1 = Flux.range(0, 10);
        var f2 = Flux.range(11, 20);

        // 두 개만 가능
        Flux.zip(f1, f2, (a, b) -> a + " " + b)
                .subscribe(System.out::println);

        var f3 = Flux.range(21, 30);
        Flux.zip(f1, f2, f3)
                .map(tuple -> tuple.getT1() + " " + tuple.getT2() + " " + tuple.getT3())
                .subscribe(System.out::println);
    }

    @Test
    void name1() {
        Mono.firstWithSignal(Mono.just(1), Mono.just(2))
                .subscribe(System.out::println);

        Flux.firstWithSignal(Flux.just(1), Flux.just(2))
                .subscribe(System.out::println);
    }
}
