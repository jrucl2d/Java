package com.example;

@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
