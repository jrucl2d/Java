package ch15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class p16 {
    public static void main(String[] args) {
        String filename = "test.txt";
        try {
            FileInputStream fis = new FileInputStream(filename);
            FileReader fr = new FileReader(filename);

            int data = 0;
            while((data = fis.read()) != -1){
                System.out.print((char)data);
            }
            System.out.println();
            fis.close();

            while((data = fr.read()) != -1){
                System.out.print((char) data);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
