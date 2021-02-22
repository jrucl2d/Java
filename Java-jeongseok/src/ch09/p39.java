package ch09;

import java.util.StringTokenizer;

public class p39 {
    public static void main(String[] args) {
        String expression = "x=100*(200+300)/2";
        // true로 해서 구분자도 토큰으로 설정
        StringTokenizer st = new StringTokenizer(expression, "+-*/=()", true);
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
}
