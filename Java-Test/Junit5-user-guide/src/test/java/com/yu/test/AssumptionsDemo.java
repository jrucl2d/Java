package com.yu.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsDemo {

    private final Calculator calculator = new Calculator();

    @Test
    void testOnlyCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test : 개발자 workstation이 아님");
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    assertEquals(2, calculator.divide(4, 2));
                });
        // 모든 환경에서 이 assertion이 실행됨
        assertEquals(42, calculator.multiply(6, 7));
    }

    @Test
    void myCustomAssume_X() {
        // assume이 맞지 않으면 skip됨
        assumeTrue(4 - 1 == 2,
                () -> "껄껄슨");
    }
    @Test
    void myCustomAssume_O() {
        // assume이 맞다면 실행됨
        assumingThat(4 - 2 == 2,
                () -> {
                    System.out.println("실행 됨");
                    assertEquals(2, calculator.divide(4, 2));
                });
    }
}
