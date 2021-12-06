package com.example.tobybom2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@SpringBootApplication
public class FluxApplication {

    @GetMapping("/event/{id}")
    Mono<List<Event>> hello(@PathVariable long id) {
        // list를 Mono에 넣은 경우 단순히 하나의 데이터.
        List<Event> events = Arrays.asList(new Event(1L, "event1"), new Event(2L, "event2"));
        return Mono.just(events);
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // data를 Stream 형태로 받음
    Flux<Event> events() {
        final Flux<Event> f1 = Flux
                .<Event, Long>generate(() -> 1L, (id, sink) -> {
                    sink.next(new Event(System.currentTimeMillis(), "value" + id));
                    return id + 1;
                })
                .delayElements(Duration.ofSeconds(1))
                .take(10);
        final Flux<Long> interval = Flux.interval(Duration.ofSeconds(1L)).take(10);
        return Flux.zip(f1, interval).map(Tuple2::getT1);
    }

    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class, args);
    }

    @Data
    @AllArgsConstructor
    public static class Event {
        private long id;
        private String value;
    }
}
