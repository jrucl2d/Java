package com.yu.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// maven 혹은 gradle 사용시 테스트에 제외할 태그를 설정할 수 있다.
@Tag("fast")
@Tag("model")
public class TaggingDemo {
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
        System.out.println("하하하");
    }
}
