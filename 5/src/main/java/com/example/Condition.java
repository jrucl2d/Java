package com.example;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
