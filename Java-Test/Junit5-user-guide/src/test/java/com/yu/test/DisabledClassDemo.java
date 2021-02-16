package com.yu.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #99 has been fixed")
public class DisabledClassDemo {

    @Test
    void testWillBeSkipped() {
        System.out.println("Disabled가 붙은 클래스의 테스트는 모두 스킵됨");
    }
    
    @Test
    void testWillBeSkipped2() {
        System.out.println("이것도 스킵");
    }
}
