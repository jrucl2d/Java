package com.example.reactorcore;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class FluxAndMono {
    public static void main(String[] args) {
        var seq1 = Flux.just("foo", "bar", "foobar");
        seq1.subscribe(System.out::println); // 데이터 처리

        var iterable = Arrays.asList("foo", "bar", "foobar");

        var seq2 = Flux.fromIterable(iterable);
        seq2.subscribe(System.out::println, System.err::println); // 데이터 처리, 에러 반응

        var noData = Mono.empty();
        noData.subscribe(System.out::println, System.err::println
            , () -> System.out.println("정상")); // 데이터 처리, 에러 반응, 시ㅝㄴ스 완료시 특정 코드 실행

        var data = Mono.just("foo");
        data.subscribe();

        var numbersFromFiveToSeven = Flux.range(5, 3); // 시작 값, 생산할 아이템 수
        Disposable subscribe = numbersFromFiveToSeven.subscribe();
        subscribe.dispose(); // 리턴 값이 Disposable 이므로 이것으로 구독 취소 가능
        // 마지막에 subscription을 다루는 Consumer가 포함된 subscribe는 deprecated 됨
//        numbersFromFiveToSeven.subscribe(
//            System.out::println,
//            System.err::println,
//            () -> System.out.println("정상"),
//            subscription -> {
//                System.out.println(subscription);
//            }
//        );
    }
}
