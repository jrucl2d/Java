package com.example;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}
