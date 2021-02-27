package ch15;

import java.io.*;

public class p38 {
    public static void main(String[] args) {
        String mergeFilename = "haha.txt";
        try {
            File tmpFile = File.createTempFile("~mergetmp", ".tmp");
            tmpFile.deleteOnExit(); // 종료시 삭제

            FileOutputStream fos = new FileOutputStream(tmpFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            BufferedInputStream bis = null;
            int number = 0;
            File f = new File(mergeFilename + "_" + number + ".txt");

            while(f.exists()){
                f.setReadOnly(); // 작업중에 파일 내용이 변경되지 않도록 한다.
                bis = new BufferedInputStream(new FileInputStream(f));

                int data = 0;
                while((data = bis.read()) != -1) {
                    bos.write(data);
                }
                bis.close();
                f = new File(mergeFilename + "_" + ++number + ".txt");
            }
            bos.close();
            File oldFile = new File(mergeFilename + ".txt");
            tmpFile.renameTo(oldFile);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

    }
}