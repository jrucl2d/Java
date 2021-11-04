package com.example.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class Part03StepVerifierTest {
    // StepVerifier 를 사용해서 Flux 내부 검증
    @Test
    void name() {
        Flux<String> flux = Flux.just("foo", "bar");
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
//                .expectComplete()
//                .verify();
                .verifyComplete(); // 위 두 개를 합쳐서
    }

    @Test
    void name1() {
        Flux<String> flux = Flux.just("foo", "bar");
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyError(RuntimeException.class); // exception 이 발생할 것을 verifying
    }

    @Test
    void name2() {
        Flux<User> flux = Flux.just(new User("swhite"), new User("jpikman"));
        StepVerifier.create(flux)
                .assertNext(u -> assertThat(u.getUsername()).isEqualTo("swhite"))
                .assertNext(u -> assertThat(u.getUsername()).isEqualTo("jpikman"))
                .verifyComplete();
    }

    @Test
    void name3() {
        // 데이터 보내는 데 걸리는 시간 만큼 테스트 시간이 소요됨
        // 0, 1, 2 ...
        Flux<Long> flux = Flux.interval(Duration.ofMillis(100))
                .take(10);

        StepVerifier.create(flux)
                .expectNextCount(10) // 10개 받을 것이라고 예측
                .verifyComplete();
    }

    @Test
    void name4() {
        // StepVerifier 가 제공하는 virtual time 을 사용해 실제 시간보다 더 빠르게 검증
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofHours(3))).expectSubscription()
                .expectNoEvent(Duration.ofHours(2)) // 2 시간 동안 아무 일도 없음을 검증
                .thenAwait(Duration.ofHours(1)) // 그 뒤 한 시간 대기
                .expectNextCount(1) // 데이터 하나
                .verifyComplete();
    }

    // Flux 혹은 Mono 에서 map 이라는 메소드를 사용해서 transform 할 수 있다.
    @Test
    void name5() {
        StepVerifier.create(Mono.just("hello").map(s -> s.toUpperCase(Locale.ROOT)))
                .expectNext("HELLO")
                .verifyComplete();

        StepVerifier.create(Flux.just(new User("haha"))
                        .map(u -> new User(u.getUsername().toUpperCase(Locale.ROOT))))
                .assertNext(u -> assertThat(u.getUsername()).isEqualTo("HAHA"))
                .verifyComplete();
    }

    // 비동기적으로 실행하므로 map 처리보다 더 빠른 flatMap 사용
    @Test
    void name6() {
        StepVerifier.create(Flux.just(new User("hihi"))
                .flatMap(this::asyncCapitalizeUser))
                .assertNext(u -> assertThat(u.getUsername()).isEqualTo("HIHI"))
                .verifyComplete();
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(Locale.ROOT)));
    }

    static class User {
        public static final User SKYLER = new User("Skyler");
        public static final User JESSE = new User("Jesse");
        private String username;

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}