package ch08;

public class p22 {
    public static void main(String[] args) {
        try{
            method1();
        } catch (Exception e){
            System.out.println("main exception");
        }
    }
    static void method1 () throws Exception {
        try{
            throw new Exception();
        } catch (Exception e) {
            System.out.println("p22.method1 exception");
            throw e;
        }
    }
}
