package com.example.reactive;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class JavaObservableExample {
    public static void main(String[] args) {
        Observer ob = (o, arg) -> System.out.println(arg);

        IntObservable io = new IntObservable();
        io.addObserver(ob);

        io.run();
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
