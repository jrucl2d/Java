package ch08;

public class p13 {
    public static void main(String[] args) {
        method1();

        try {
            method2();
        } catch (Exception e) {
            System.out.println("p13.main에서 예외 처리함");
            e.printStackTrace();
        }
    }
    static void method1 () {
        try {
            throw new Exception();
        } catch (Exception e){
            System.out.println("p13.method1에서 예외 처리함");
            e.printStackTrace();
        }
    }
    static void method2() throws Exception {
        throw new Exception("exception");
    }
}
