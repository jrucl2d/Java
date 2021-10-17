package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static reactor.core.scheduler.Schedulers.parallel;

public class FlatMapMoreTest {
    // flatMap -> 비동기적으로 실행되지만 순차적이지 않음
    // flatMap 대신 concatMap 사용하면 순차적이지만 사실상 비동기의 이점 X -> 느려짐
    // flatMapSequential -> 순차적이면서 속도도 빠름.
    // ㄴ> A,B,C 처리하는 동안 뒤의 D,E,F / G,H,I 쪽도 모두 trigger 되었지만 최종 때 합쳐서 결과를 보냄
    @Test
    void name() {
        Flux.just("a", "b", "c", "d", "e", "f", "g", "h", "i")
                .window(3)
//                .flatMap(l -> l.map(this::toUpperCase)) // 비동기 없이 동기로 처리됨
                .flatMapSequential(l -> l.map(this::toUpperCase).subscribeOn(parallel())) // 비동기적으로 실행됨
                .doOnNext(System.out::println)
                .blockLast();
    }

    private List<String> toUpperCase(String s) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(s.toUpperCase(Locale.ROOT), Thread.currentThread().getName());
    }
}
