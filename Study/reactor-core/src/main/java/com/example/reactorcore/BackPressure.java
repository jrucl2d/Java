package com.example.reactorcore;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressure {
    public static void main(String[] args) {
        Flux.range(1, 10)
            .doOnRequest(r -> System.out.println("request of " + r))
            .subscribe(new BaseSubscriber<>() {
                // BaseSubscriber로 재정의시 적어도 한 번은 request를 호출해야 한다.
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    System.out.println("Cancelling after having received " + value);
                    cancel();
                }
            });
    }
}
