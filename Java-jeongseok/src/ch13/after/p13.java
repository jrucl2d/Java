package ch13.after;

import javax.swing.*;

public class p13 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread3());
        t1.start();
        String input = JOptionPane.showInputDialog("hello");
        System.out.println("input = " + input);
        t1.interrupt();
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
    }
}
class Thread3 implements Runnable {
    public void run() {
        int i = 10;
        while(i != 0 && !Thread.currentThread().isInterrupted()) {
            System.out.println("i = " + i--);
            for(long x = 0; x < 2500000000L; x++);
        }
        System.out.println("Thread3.run end");
    }
}