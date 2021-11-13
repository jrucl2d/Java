package ch13.after;

import javax.swing.*;

public class p14 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread4());
        t1.start();
        String input = JOptionPane.showInputDialog("hello");
        System.out.println("input = " + input);
        t1.interrupt();
        System.out.println("t1.isInterrupted() = " + t1.isInterrupted());
    }
}
class Thread4 implements Runnable {
    public void run() {
        int i = 10;
        while(i != 0 && !Thread.currentThread().isInterrupted()) {
            System.out.println("i = " + i--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread4.run end");
    }
}