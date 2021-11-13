package ch09;

import java.util.StringTokenizer;

public class p38 {
    public static void main(String[] args) {
        String src = "100,200,300,400";
        StringTokenizer st = new StringTokenizer(src, ",");

        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
}
