package com.example;

public interface ConditionalAction {
    boolean evaluate(Facts facts);
    void perform(Facts facts);
}
