package ch07.before;

public class p7_16 {
    public static void main(String[] args) {
        FireEngine fe = new FireEngine();

        if(fe instanceof FireEngine){
            System.out.println("FireEngine Instance");
        }
        if(fe instanceof Car){
            System.out.println("Car Instance");
        }
        if(fe instanceof Object){
            System.out.println("Object Instance");
        }
        System.out.println(fe.getClass().getName()); // 클래스 이름 리턴
    }
}
