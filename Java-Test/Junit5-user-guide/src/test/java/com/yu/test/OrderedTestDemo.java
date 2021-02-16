package com.yu.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// MethodOrderer 인터페이스를 구현한 커스텀 순서 클래스를 생성 후 properties 설정파일 생성
// e.g. src/test/resources/junit-platform.properties에
// junit.jupiter.testmethod.order.default = org.junit.jupiter.api.MethodOrderer$OrderAnnotation
// @TestMethodOrder가 없는 테스트에는 설정 파일에 지정된 기본 순서 클래스가 적용됨
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTestDemo {
    // @Order의 숫자에 맞는 순서대로 실행됨
    @Test
    @Order(2)
    void second(){}
    @Test
    @Order(1)
    void first(){}
    @Test
    @Order(3)
    void third(){}
}
