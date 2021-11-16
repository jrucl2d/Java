package com.example.reactorcore;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BaseSubscriberExample {
    public static void main(String[] args) {
        var ss = new SampleSubscriber<Integer>();
        var ints = Flux.range(1, 4);
        ints.subscribe(System.out::println,
            err -> System.err.println("Error" + err),
            () -> System.out.println("Done"));
        ints.subscribe(ss);
    }

    public static class SampleSubscriber<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }
}
