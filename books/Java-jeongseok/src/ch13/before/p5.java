package ch13.before;

import javax.swing.*;

class Thread5 extends Thread{
    @Override
    public void run() {
        for(int i = 10; i > 0; i--){
            System.out.println("i = " + i);
            try{
                sleep(1000);
            } catch (Exception e){}
        }
    }
}
public class p5 {
    public static void main(String[] args) {
        Thread5 t1 = new Thread5();
        t1.start();
        
        String input = JOptionPane.showInputDialog("say something");
        System.out.println("input = " + input);
    }
}
