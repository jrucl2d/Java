package com.example.reactive.reactive;

import reactor.core.publisher.Flux;

public class ReactorExample {
    public static void main(String[] args) {
        Flux.<Integer>create(s -> {
            s.next(1);
            s.next(2);
            s.next(3);
        })
                .log()
                .map(s -> s * 10)
                .subscribe(System.out::println);
    }
}
