package com.example.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generic {
    // T : Type variable
    static class Hello<T> { // type parameter

    }

    public static void main(String[] args) {
        new Hello<String>(); // type argument

        List list = Arrays.asList(1,2,3); // 컴파일 시점에 타입을 체킹하지 못함.

        List list1 = new ArrayList<Integer>(); // raw type으로 사용
        List<Integer> ints = Arrays.asList(1,2,3);
        List list2 = ints;
    }
}
