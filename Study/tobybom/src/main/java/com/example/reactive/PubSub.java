package com.example.reactive;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class PubSub {
    public static void main(String[] args) throws InterruptedException {
        // Publisher <- Observable
        // Subscriber <- Observer

        // DB에서 가져온 컬렉션 데이터라고 생각할 수 있다.
        Iterable<Integer> itr = Arrays.asList(1,2,3,4,5);
        var es = Executors.newSingleThreadExecutor();

        var p = new Flow.Publisher<Integer>() {

            @Override
            public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
                Iterator<Integer> it = itr.iterator();

                subscriber.onSubscribe(new Flow.Subscription() {
                    @Override
                    public void request(long n) {
                        es.execute(() -> {
                            int i = 0;
                            try {
                                while (i++ < n) {
                                    if (it.hasNext()) {
                                        subscriber.onNext(it.next());
                                    } else {
                                        subscriber.onComplete();
                                        break;
                                    }
                                }
                            } catch (RuntimeException e) {
                                subscriber.onError(e);
                            }
                        });
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        var s = new Flow.Subscriber<Integer>() {
            Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("onSubscribe");
                subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("onNext " + item);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        p.subscribe(s);
        Thread.sleep(2000);
        es.shutdown();
    }
}
