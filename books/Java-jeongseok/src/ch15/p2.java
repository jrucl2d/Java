package ch15;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class p2 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] tmp = new byte[10]; // 배열을 사용해 입출력 작업의 효율을 증가

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();
        
        input.read(tmp, 0, tmp.length); // 읽어온 데이터를 tmp에 담는다.
        output.write(tmp, 5, 5); // tmp[5]부터 5개의 데이터를 write
        
        outSrc = output.toByteArray();

        System.out.println("Arrays.toString(inSrc) = " + Arrays.toString(inSrc));
        System.out.println("Arrays.toString(tmp) = " + Arrays.toString(tmp));
        System.out.println("Arrays.toString(outSrc) = " + Arrays.toString(outSrc));
    }
}
