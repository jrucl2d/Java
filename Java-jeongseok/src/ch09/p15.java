package ch09;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class p15 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "가";

        byte[] barr = str.getBytes(StandardCharsets.UTF_8);
        byte[] barr2 = str.getBytes("CP949");

        System.out.println("barr = " + joinByteArr(barr));
        System.out.println("barr2 = " + joinByteArr(barr2));

        System.out.println(new String(barr, "UTF-8"));
        System.out.println(new String(barr2, "CP949"));
    }
    static String joinByteArr(byte[] bArr) {
        StringJoiner sj = new StringJoiner(":", "[", "]");
        for(byte b : bArr){
            sj.add(String.format("%02X",b));
        }
        return sj.toString();
    }
}
