package ch07.after;

public class p7_29 {
    public static void main(String[] args) {
        A2 a = new A2();
        a.methodA();
    }
}
class A2{
    void methodA() {
        I2 i = InstanceManager.getInstance();
        i.methodB();
        System.out.println(i.toString());
    }
}
interface I2 {
    public abstract void methodB();
}
class B2 implements I2 {
    public void methodB() {
        System.out.println("B.methodB");
    }
    public String toString() {return "class B";}
}
class InstanceManager {
    public static I2 getInstance() {
        return new B2(); // 다른 인스턴스로 변경시 여기만 바꾸면 됨. 스프링 빈 같은 느낌
    }
}