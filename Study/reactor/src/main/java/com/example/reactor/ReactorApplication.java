package com.example.reactor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactorApplication {

    public static void main(String[] args) {

        Flux<String> flux = Flux.just("A");
        flux.map(s -> "foo" + s); // 중간 publisher 가 생긴다.
        flux.subscribe(System.out::println); // 이전의 flux 에 대해 구독하므로 A 만 출력됨

        flux = flux.map(s -> "foo" + s); // 중간 publisher 를 구독
        flux.subscribe(System.out::println); // fooA 출력됨.
    }

}
