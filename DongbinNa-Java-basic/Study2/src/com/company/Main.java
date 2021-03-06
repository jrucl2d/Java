package com.company;

public class Main {
//
//    final static double PI = 3.141592; // final이 붙으면 상수가 됨, static은 클래스 내에서 공유
//
//    public static void main(String[] args) {
//        // 기초 변수
////	    int intType = 100;
////	    double doubleType = 150.5;
////	    String stringType = "유성근";
////
////	      System.out.println(intType);
////        System.out.println(doubleType);
////        System.out.println(stringType);
//
//        int r  = 30;
//        System.out.println((r * r * PI));
//
//    }
//
//    final static int INT_MAX = 2147483647;
//    public static void main(String[] args){
//        int a = INT_MAX;
//        System.out.println(a);
//        System.out.println(a + 1); // overflow 발생!
//    }

    public static void main(String[] args){
        int a = 1;
        int b = 2;

        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b)); // 몫만 출력됨

        int c = (int) 0.5;
        System.out.println((c)); // 실수를 넣으면 정수부만 들어감

        // 반올림 하는 법 : 실수에 0.5를 더하고 int로 형변환 하면 됨
        double d = 0.5;
        int e = (int) (d + 0.5);
        System.out.println((e));
    }
}
