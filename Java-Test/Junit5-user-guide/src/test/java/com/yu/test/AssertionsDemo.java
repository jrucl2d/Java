package com.yu.test;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemo {
    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Kane", "In");

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        // 오류 메시지를 뒤에 넣을 수 있음
        assertEquals(4, calculator.multiply(2, 2), "선택적으로 오류 메시지를 마지막에 넣을 수 있음");
        assertTrue('a' < 'b', () -> "Assertion 메시지는 복잡한 메시지를 쓸데 없이 만들지 않기 위해 뒤늦게 평가됨");
    }

    @Test
    void gropuedAssertions() {
        // 그룹 assertion에서 모든 assertion들이 실행되고 모든 실패가 같이 기록됨
        assertAll("person",
                () -> assertEquals("Kane", person.getFname()),
                () -> assertEquals("In", person.getLname())
        );
    }

    @Test
    void dependentAssertions() {
        // 코드 블록 안에서, 만약 assertion이 실패하면 같은 블록 내 뒤의 코드들은 생략됨
        assertAll("properties",
                () -> {
                    String fname = person.getFname();
                    // 앞선 테스트가 성공해야 아래가 실행됨
                    assertAll("first name",
                            () -> assertTrue(fname.startsWith("K")),
                            () -> assertTrue(fname.endsWith("e"))
                    );
                },
                () -> {
                    // 그룹 assertion은 독립적으로 실행되므로 fname assertion과 독립적으로 실행된다.
                    String lname = person.getLname();
                    // 앞선 테스트가 성공해야 아래가 실행됨
                    assertAll("last name",
                            () -> assertTrue(lname.startsWith("I")),
                            () -> assertTrue(lname.endsWith("n"))
                    );
                });
    }

    @Test
    void exceptionTesting() {
        // 람다식을 실행했을 때 해당 exception이 터져야 함
        Exception exception = assertThrows(ArithmeticException.class,
                () -> calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutExceeded_X() {
        // 아래의 assertion은 성공함
        // 특정 시간동안 테스트가 끝나지 않으면 테스트를 실패시킴
        assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(1000);
        });
    }
    @Test
    void timeoutExceeded_O() {
        // 아래의 assertion은 실패함
        // 시간 초과를 하더라도 모든 테스트가 끝날때까지 기다려야 하는 단점이 있다.
        assertTimeout(Duration.ofSeconds(2), () -> {
            Thread.sleep(3000); // 2초를 초과하므로 타임아웃 테스트 실패
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // 아래의 assertion은 실패함. 아래와 같은 오류 메시지 확인 가능
        // execution timed out after 10 ms
        // 실행중 timeout을 넘으면 바로 테스트를 끝냄
        assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            new CountDownLatch(1).await();
        });
    }
}
