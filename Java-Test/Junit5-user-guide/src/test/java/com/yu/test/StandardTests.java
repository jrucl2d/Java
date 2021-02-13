package com.yu.test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class StandardTests {

    @BeforeAll // 반드시 static이어야 함
    static void initAll() {
        System.out.println("모든 테스트 이전에 실행됨");
    }
    @BeforeEach
    void init() {
        System.out.println("매 테스트 직전에 실행됨");
    }
    @Test
    void succeedingTest(){
        System.out.println("성공하는 테스트 성공");
    }
    @Test
    void failingTest(){
        System.out.println("실패하는 테스트 성공");
        fail("테스트 실패");
    }
    @Test
    @Disabled("목적을 나타내기 위해")
    void skippedTest(){
        System.out.println("실행되지 않음");
    }
    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("테스트가 중단될 것임");
    }
    @AfterEach
    void tearDown(){
        System.out.println("매 테스트 직후에 실행됨");
    }
    @AfterAll
    static void tearDownAll(){
        System.out.println("모든 테스트 이후에 실행됨");
    }
}


