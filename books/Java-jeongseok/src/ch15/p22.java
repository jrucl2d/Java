package ch15;

import java.io.IOException;

public class p22 {
    public static void main(String[] args) {
        try{
            int input = 0;
            while((input = System.in.read()) != -1) {
                System.out.println("input = " + input + " char = " + (char)input);
            }
        } catch (IOException e) {}
    }
}
