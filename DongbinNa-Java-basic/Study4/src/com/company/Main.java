package com.company;

public class Main {

    final static int SEC = 1000;
    public static void main(String[] args) {

        int minute = SEC / 60;
        int second = SEC % 60;
        System.out.println(minute + "분" + second + "초");

        double a = Math.pow(3.0, 20.0); // Math 안의 pow 함수 사용
        System.out.println("3의 20제곱은 " + (int)a);
    }
}
