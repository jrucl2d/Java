package ch09;

public class p16 {
    public static void main(String[] args) {
        int ival = 100;
        String strval = String.valueOf(ival);

        double dval = 200.0;
        String strval2 = dval + "";
        // 부호를 나타내는 + 는 사용 가능
        double sum = Integer.parseInt("+"+strval) + Double.parseDouble(strval2);
        System.out.println(String.join("", strval,"+", strval2, "=") + sum);

        double sum2 = Integer.valueOf(strval) + Double.valueOf(strval2);
        System.out.println(strval + "+" + strval2 +"=" + sum2);
    }
}
