package com.example.reactive.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PubSub {
    public static void main(String[] args) {
        Iterable<Integer> iter = Stream.iterate(1, a -> a+1).limit(5).collect(Collectors.toList());

        Publisher<Integer> pub = s -> s.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                try {
                    iter.forEach(s::onNext);
                    s.onComplete();
                } catch (Throwable t) {
                    s.onError(t);
                }
            }

            @Override
            public void cancel() {

            }
        });

        Subscriber<Integer> sub = new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext : " + integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError : " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        pub.subscribe(sub);
    }
}
