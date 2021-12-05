package com.example.tobybom2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@SpringBootApplication
public class Tobybom2Application {

	// Publisher -> (Publisher) -> (Publisher) -> Subscriber 여기서 log()는 중간 Publisher라고 할 수 있다.
	// pos1 -> pos2 -> Hello WebFlux : doOnNext, log 등의 부가적인 작업은 Subscriber가 subscribe()라고 사용한 시점에 실행된다.
	@GetMapping("/")
	Mono<String> hello() {
		log.info("pos1");
		var m = Mono.just("Hello WebFlux") // just 자체는 동기적으로 실행된다. 즉시 데이터를 생산함.
				.doOnNext(log::info)
				.log();
		log.info("pos2");
		return m;
	}

	public static void main(String[] args) {
		SpringApplication.run(Tobybom2Application.class, args);
	}

}
