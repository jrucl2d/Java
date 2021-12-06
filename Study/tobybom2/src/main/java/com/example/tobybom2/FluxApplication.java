package com.example.tobybom2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/events")
    Flux<Event> events() {
        // Flux로 여러 데이터를 처리. 각 요소에 대해서 map 등을 사용해서 처리 가능.
        return Flux.just(new Event(1L, "event1"), new Event(2L, "event2"));
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
