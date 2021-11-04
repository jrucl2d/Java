package com.example.reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class BackpressureMore {
    @Test
    void name() {
        Flux.range(1, 100) // 기본적으로 request는 unbounded로.
                .log()
                .doOnNext(System.out::println)
                .subscribe();
    }

    @Test
    void name1() {
        // WebFlux를 쓰면 직접 backpressure 조절 필요 없이 flux만 넘기면 알아서 request 보냄. 31로 기본값이라고 함.
        Flux.range(1, 100)
                .log()
                .doOnNext(System.out::println)
                .subscribe(new Subscriber<>() {
                    private Subscription subscription;
                    private int count = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        this.subscription.request(10); // 10개 달라고 request 설정
                    }

                    @Override
                    public void onNext(Integer integer) {
                        count++;
                        // 10개 받고 나면 다시 10개 pulling
                        if (count % 10 == 0) {
                            this.subscription.request(10);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
