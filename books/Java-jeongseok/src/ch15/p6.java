package ch15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class p6 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]); // 텍스트 파일을 다룰 때는 FileReader, FileWriter가 낫다.
            FileOutputStream fos = new FileOutputStream(args[1]);
            int data = 0;
            while((data = fis.read()) != -1){
                fos.write(data);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
