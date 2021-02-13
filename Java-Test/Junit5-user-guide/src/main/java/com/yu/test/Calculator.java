package com.yu.test;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }


    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) throws ArithmeticException{
        return a / b;
    }
}