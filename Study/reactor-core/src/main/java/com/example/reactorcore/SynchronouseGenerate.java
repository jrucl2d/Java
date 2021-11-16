package com.example.reactorcore;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

public class SynchronouseGenerate {
    public static void main(String[] args) {
        var flux = Flux.generate(
            () -> 0, // 초기값
            (state, sink) -> {
                sink.next("3 x " + state + " = " + 3 * state); // 상태를 보고 방출할 데이터 결정
                if (state == 10) sink.complete(); // 중단 결정에도 상태를 이용함
                return state + 1; // 다음 상태의 값을 반환
            }
        );
        flux.subscribe(System.out::println);

        Flux.generate(
            AtomicLong::new,
            (state, sink) -> {
                long i = state.getAndIncrement();
                sink.next("3 x " + i + " = " + 3 * i);
                if (i == 10) sink.complete();
                return state;
            },
            state -> System.out.println("state: " + state) // 마지막 상태 값 확인
        ).subscribe(System.out::println);
    }
}
