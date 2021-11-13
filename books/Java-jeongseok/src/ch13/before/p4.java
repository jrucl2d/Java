package ch13.before;

import javax.swing.*;

public class p4 {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("say something");
        System.out.println("input = " + input);
        for(int i =10; i > 0; i--){
            System.out.println(i);
            try{
                Thread.sleep(1000);
            }
            catch (Exception e){}
        }
    }
}
