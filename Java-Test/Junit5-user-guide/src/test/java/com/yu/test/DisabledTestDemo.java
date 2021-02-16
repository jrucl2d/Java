package com.yu.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledTestDemo {
    @Disabled("Disabled until bug #42 has been resolved") // Disabled 된 이유는 필수는 아니지만 적는 것이 좋다.
    @Test
    void testWillBeSkipped() {
        System.out.println("스킵될 운명");
    }

    @Test
    void testWillBeExecuted() {
        System.out.println("실행될 운명");
    }
}
