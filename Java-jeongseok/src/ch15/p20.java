package ch15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class p20 {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader(".gitignore");
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            for(int i = 1; (line = br.readLine()) != null; i++){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {} catch (IOException e) {

        }
    }
}
