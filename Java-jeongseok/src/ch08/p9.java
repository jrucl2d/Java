package ch08;

public class p9 {
    public static void main(String[] args) {
        try{
            Exception e = new Exception("고의로 발생시킨 예외");
            throw e;
        } catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("프로그램 정상 종료");
    }
}
