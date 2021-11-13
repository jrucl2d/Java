package ch07.before;

public class p7_18 {
    public static void main(String[] args) {
        Parent3 p = new Child3();
        Child3 c = new Child3();

        // 참조변수의 타입에 의해 다른 값이 출력
        System.out.println("p.x = " + p.x);
        System.out.println("c.x = " + c.x);

        // 무조건 오버라이딩 된 메소드가 호출
        p.method();
        c.method();
    }
}
class Parent3{
    int x = 100;
    void method() {
        System.out.println("Parent3.method");
    }
}
class Child3 extends Parent3{
    int x = 200;
    void method() {
        System.out.println("Child3.method");
    }
}