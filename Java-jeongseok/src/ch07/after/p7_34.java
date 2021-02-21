package ch07.after;

class Outer {
    class InstanceInner {
        int iv = 100;
    }
    static class StaticInner {
        int iv = 200;
        static int cv = 300;
    }
    void myMethod() {
        class LocalInner {
            int iv = 400;
        }
    }
}
public class p7_34 {
    public static void main(String[] args) {
        Outer oc = new Outer(); // 내부 클래스의 인스턴스를 생성하려면 외부 클래스 인스턴스 먼저 생성해야 함
        Outer.InstanceInner ii = oc.new InstanceInner();

        System.out.println("ii.iv = " + ii.iv);
        System.out.println("Outer.StaticInner.cv = " + Outer.StaticInner.cv);
        
        // 스태틱 내부 클래스의 인스턴스는 외부 클래스를 먼저 생성하지 않아도 됨 -> DTO 사용시 static으로 하는 이유
        Outer.StaticInner si = new Outer.StaticInner();
        System.out.println("si.iv = " + si.iv);
    }
}
