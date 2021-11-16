package com.example.reactorcore;

import reactor.core.publisher.Flux;

public class SubscribeExample {
    public static void main(String[] args) {
        // 아무것도 표시되지 않지만 값 3개가 발행 된다.
        var ints = Flux.range(1, 3);
        ints.subscribe();

        ints.subscribe(System.out::println);

        var ints2 = Flux.range(1, 4)
            .map(i -> {
                if (i <= 3) return i;
                throw new RuntimeException("Got to 4"); // 의도적으로 에러 발새
            });
        ints2.subscribe(System.out::println,
            err -> System.out.println("Error" + err));

        // 에러 신호와 완료 신호는 서로 상호 배타적이므로 Error / Done 중 하나만 받을 수 있다.
        ints.subscribe(System.out::println,
            err -> System.err.println("Error" + err),
            () -> System.out.println("Done")
        );
    }
}
