package com.example.reactive.javaversion;

import java.util.Iterator;

public class IterableExample {
    public static void main(String[] args) {
        Iterable<Integer> iter = () -> new Iterator<>() {
            int i = 0;
            final static int MAX = 10;

            @Override
            public boolean hasNext() {
                return i < MAX;
            }

            @Override
            public Integer next() {
                return ++i;
            }
        };

        for (Integer integer : iter) {
            System.out.println(integer);
        }

        // 이전에는
        for (Iterator<Integer> it = iter.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }

        // Iterable (pull) <---> Observable (push) <= duality
    }
}
