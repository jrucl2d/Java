package ch15;

import java.io.*;

public class p37 {
    public static void main(String[] args) {
        final int VOLUME = 10000;

        String filename = "haha.txt";
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            int data = 0;
            int i = 0;
            int number = 0;

            while((data = bis.read()) != -1) {
                if(i % VOLUME == 0){
                    if(i != 0){
                        bos.close();
                    }
                    fos = new FileOutputStream(filename + "_" + ++number + ".txt");
                    bos = new BufferedOutputStream(fos);
                }
                bos.write(data);
                i++;
            }
            bis.close();
            fis.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }
}
