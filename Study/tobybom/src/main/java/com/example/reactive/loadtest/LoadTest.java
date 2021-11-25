package com.example.reactive.loadtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LoadTest {
    private static final AtomicInteger it = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService es = Executors.newFixedThreadPool(100);

        var rt = new RestTemplate();
        String url = "http://localhost:8080/";

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 100; i++) {
            es.execute(() -> {
                final int index = it.addAndGet(1);
                log.info("thread" + index);

                StopWatch inner = new StopWatch();
                inner.start();
                rt.getForObject(url, String.class);

                inner.stop();
                log.info("Elapsed " + index + " " + inner.getTotalTimeSeconds());
            });

            es.awaitTermination(100, TimeUnit.SECONDS);
            es.shutdown();
            stopWatch.stop();

            log.info("Total : {}", stopWatch.getTotalTimeSeconds());
        }
    }
}
