package com.yu.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

//    // 커스텀 어노테이션
//    @Target(ElementType.METHOD)
//    @Retention(RetentionPolicy.RUNTIME)
//    @Test
//    @EnabledOnOs(MAC)
//    @interface TestOnMac {
//    }

public class ConditionalTestDemo {
    // OS 조건
    @Test
    @EnabledOnOs(OS.MAC)
//    @TestOnMac -> 커스텀 어노테이션 사용
    void onlyOnMacOs(){
        System.out.println("맥에서만 실행");
    }
    @Test
    @EnabledOnOs({OS.LINUX, OS.WINDOWS})
    void onLinuxOrMac() {
        System.out.println("리눅스나 윈도우에서만 실행");
    }
    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnWindows() {
        System.out.println("윈도우에서는 실행 안 됨");
    }
    
    // JRE 버전 조건
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        System.out.println("자바 8에서만 실행됨");
    }
    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_12, JRE.JAVA_14})
    void for81214() {
        System.out.println("8, 12, 14에서 실행됨");
    }
    @Test
    @EnabledForJreRange(min = JRE.JAVA_12, max = JRE.JAVA_14)
    void rangeYes() {
        System.out.println("12~14면 실행됨");
    }
    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void onlyNotOnJava8() {
        System.out.println("자바 8에서만 실행 안 됨");
    }
    @Test
    @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_15)
    void rangeNo() {
        System.out.println("8~15 사이면 실행 안 됨");
    }
    @Test
    @DisabledForJreRange(min = JRE.JAVA_12)
    void rangeNo2() {
        System.out.println("12 이상부터 실행 안 됨");
    }
    // 시스템 변수 조건(JVM 시스템 변수)
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onLyOn64bitArchitectures() {
        System.out.println("64비트 따리만");
    }
    // 환경 변수 조건
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() {
        System.out.println("staging 서버에서만");
    }
    // 커스텀 조건
    @Test
    @EnabledIf("customCondition")
    void customEnabled() {
        System.out.println("무조건 실행!");
    }
    @Test
    @DisabledIf("customCondition")
    void customDisabled() {
        System.out.println("무조건 안 실행!");
    }
    boolean customCondition() {
        return true;
    }
}
