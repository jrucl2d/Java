package ch15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class p3 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] tmp = new byte[4]; // 이전 예제와 배열 크기가 다름

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        System.out.println("Arrays.toString(inSrc) = " + Arrays.toString(inSrc));

        try{
            while(input.available() > 0) {
                // tmp에 남은 6, 7이 결과 outSrc에 들어가는 불상사 발생
//                input.read(tmp);
//                output.write(tmp);
                int len = input.read(tmp);
                output.write(tmp, 0, len); // 읽어온 길이만큼만 output에 write
                
                outSrc = output.toByteArray();
                System.out.println("Arrays.toString(tmp) = " + Arrays.toString(tmp));
                System.out.println("Arrays.toString(outSrc) = " + Arrays.toString(outSrc));
            }
        } catch (IOException e) {}
    }
}
