package com.example.generic.generic2;

import java.io.Closeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {
    // 여러 개를 줄 수 있음. 인터페이스는 여러 개 가능. 클래스는 하나.
    static <T extends List & Comparable & Closeable & Serializable> void print(T t) {

    }

    static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
        return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0).count();
    }

//    static class MyList<E, P> implements List<E> {
//
//    }

    public static void main(String[] args) {
//        Integer[] arr = new Integer[] {1,2,3,4,5,6,7};
//        String[] arr = new String[] {"1","2","3","4","5","6","7"};
//        System.out.println(countGreaterThan(arr, "4"));

//        Integer i = 10;
//        Number n = i;
//        List<Integer> integerList = new ArrayList<>();
//        List<Number> numberList = integerList; // 컴파일 에러 발생. 타입 파라미터 주어진 컬렉션 사이의 다형성 X

//        ArrayList<Integer> integerArrayList = new ArrayList<>();
//        List<Integer> list = integerArrayList; // 정상.

        // 둘 다 정상
//        List<String> s1 = new MyList<String, Integer>();
//        List<String> s2 = new MyList<String, String>();
    }
}
