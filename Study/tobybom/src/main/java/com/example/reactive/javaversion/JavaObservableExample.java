package com.example.reactive.javaversion;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;

@SuppressWarnings("deprecation")
public class JavaObservableExample {
    public static void main(String[] args) {
        Observer ob = (o, arg) -> System.out.println(Thread.currentThread().getName() + arg);

        var io = new IntObservable();
        io.addObserver(ob);

        var es = Executors.newSingleThreadExecutor();
        es.submit(io);

        System.out.println(Thread.currentThread().getName() + " EXIT");
        es.shutdown();

        // 1. Complete 어떻게 처리??
        // 2. Error 어떻게 처리??
    }

    static class IntObservable extends Observable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                setChanged();
                // duality로 같은 기능을 제공하지만 반대된다. DATA method(void) <-> void method(DATA)
                notifyObservers(i);   // push
                // int i = it.next(); // pull
            }
        }
    }
}
