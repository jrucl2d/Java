package com.example;

@FunctionalInterface
public interface Action {
    void perform(Facts facts);
}
