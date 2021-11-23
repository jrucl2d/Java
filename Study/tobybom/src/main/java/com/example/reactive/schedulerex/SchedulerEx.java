package com.example.reactive.schedulerex;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SchedulerEx {
    public static void main(String[] args) {
        Publisher<Integer> pub = sub -> sub.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                sub.onNext(1);
                sub.onNext(2);
                sub.onNext(3);
                sub.onNext(4);
                sub.onNext(5);
                sub.onComplete();
            }

            @Override
            public void cancel() {

            }
        });

        // 기존의 pub를 중개하면서 새로운 스레드에서 실행해줌.
        Publisher<Integer> subOnPub = sub -> {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> pub.subscribe(sub));
        };

        subOnPub.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                log.debug("onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.debug("onNext " + integer);
            }

            @Override
            public void onError(Throwable t) {
                log.debug("onError ", t);
            }

            @Override
            public void onComplete() {
                log.debug("onComplete");
            }
        });
        log.debug("exit");
    }
}
