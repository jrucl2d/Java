package com.example.reactorcore;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class ErrorHandling {
    public static void main(String[] args) throws InterruptedException
    {
        Flux.just(1, 2, 0)
            .map(i -> "100 / " + i + " = " + (100 / i))
            .onErrorReturn("Divided by zero :(") // catch 해서 default 스태틱 값을 리턴한다.
//            .onErrorReturn(e -> e.getMessage().equals("boom10"), "recovered10") // Predicate를 적용해서 복구 여부 결정 가능
            .subscribe(System.out::println);

        // try catch와 유사함
        Flux.range(0, 10)
            .map(v -> v / 0)
            .map(v -> v + 10)
            .subscribe(System.out::println,
                err -> System.out.println("error " + err));

        AtomicBoolean isDisposed = new AtomicBoolean();
        var disposableInstance = new Disposable() {

            @Override
            public void dispose() {
                isDisposed.set(true);
            }

            @Override
            public String toString() {
                return "DISPOSABLE";
            }
        };

        Flux.using(
            () -> disposableInstance, // 리소스 생성
            disposable -> Flux.just(disposable.toString()), // 리소스 처리해서 Flux<T> 리턴
            Disposable::dispose // 리소스를 정리. 위의 Flux가 종료되거나 취소되면 호출된다.
        );

        Flux.interval(Duration.ofMillis(250))
            .map(input -> {
                if (input < 3) return "tick " + input;
                throw new RuntimeException("boom");
            })
            .retry(1) // 업스트림 flux를 재구독하는 것. 기존 시퀀스는 종료된다.
            .elapsed() // 데이터와 이전 데이터를 방출한 이후 소요된 시간을 한군데 묶음.
            .subscribe(System.out::println, System.err::println); // onError 신호도 확인 가능.

        Thread.sleep(2100);
    }
}
