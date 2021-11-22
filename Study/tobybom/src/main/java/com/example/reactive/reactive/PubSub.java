package com.example.reactive.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Publisher -> [Data1] -> Operator -> [Data2] -> Operator2 -> [Data3] -> Subscriber
 * 1. map (d1 -> f -> d2)
 */
public class PubSub {
    public static void main(String[] args) {
        Iterable<Integer> iter = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());

        Publisher<Integer> pub = iterPub(iter);
        Publisher<Integer> mapPub = mapPub(pub, s -> s * 10);
        Publisher<Integer> mapPub2 = mapPub(mapPub, s -> -s);
        
        Publisher<Integer> sumPub = sumPub(pub);
        Subscriber<Integer> sub = logSub();
        sumPub.subscribe(sub);
    }

    private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
        return sub -> {
            pub.subscribe(new DelegateSub((Subscriber<Integer>) sub) {
                int sum = 0;

                @Override
                public void onNext(Integer integer) {
                    sum += integer;
                }

                @Override
                public void onComplete() {
                    sub.onNext(sum);
                    sub.onComplete();
                }
            });
        };
    }

    private static Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> f) {
        // subscription을 전달 받아서 새로운 subscription으로 건네주는 중개 역할
        return sub -> pub.subscribe(new DelegateSub((Subscriber<Integer>) sub) {
            @Override
            public void onNext(Integer integer) {
                sub.onNext(f.apply(integer));
            }
        });
    }

    private static Subscriber<Integer> logSub() {
        return new Subscriber<>() {
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
    }

    private static Publisher<Integer> iterPub(Iterable<Integer> iter) {
        return s -> s.onSubscribe(new Subscription() {
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
    }
}
