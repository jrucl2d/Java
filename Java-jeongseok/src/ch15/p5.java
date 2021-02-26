package ch15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class p5 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(args[0]);

        int data = 0;

        while((data = fis.read()) != -1){
            char c = (char) data;
            System.out.print(c);
        }
    }
}
