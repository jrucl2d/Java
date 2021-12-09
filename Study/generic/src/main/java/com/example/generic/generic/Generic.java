package com.example.generic.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generic {
    // T : Type variable
    static class Hello<T> { // type parameter

        // static 메소드는 객체 생성 전에 사용하므로 T가 어떤 것이 들어갈지 모른다.
//        static void doSomething(T t) {
//        }

        // 따라서 메소드 레벨의 타입 파라미터 사용해야 함.
        static <S> void doSomething(S s) {

        }

        // 메소드 레벨의 S를 받아서 클래스 레벨의 T로 리턴
        <S> T print(S s) {
            return null;
        }

        // 생성자에도 타입 파라미터 받을 수 있음
        public <S> Hello(S s) {
        }
    }

    // 클래스 레벨이 아닌 메소드 레벨에서 타입 파라미터 정의
    <T> void print(T t) {
        System.out.println(t.toString());
    }

    // static 메소드를 제네릭으로
    static <T> void staticPrint(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        new Generic().print("Hello");
        new Generic().print(1);
        Generic.staticPrint("Hello");
    }

    private static void list() {
        List list = Arrays.asList(1,2,3); // 컴파일 시점에 타입을 체킹하지 못함.
        List list1 = new ArrayList<Integer>(); // raw type으로 사용
        List<Integer> ints = Arrays.asList(1,2,3);
        List list2 = ints;
        List<Integer> ints2 = ints; // 괜찮음
        List<String> strs = list; // raw type의 integer 리스트를 넣을 수 있게 됨. 심각한 오류
    }
}
