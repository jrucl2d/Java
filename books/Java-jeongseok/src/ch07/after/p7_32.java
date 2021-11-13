package ch07.after;

public class p7_32 {
    class InstanceInner {}
    static class StaticInner {}

    // 인스턴스 멤버끼리 서로 직접 접근 가능
    InstanceInner iv = new InstanceInner();

    // static 멤버끼리 서로 직접 접근 가능
    static StaticInner cv = new StaticInner();

    static void staticMethod() {
        // static 멤버는 인스턴스 멤버에 접근 불가
//        InstanceInner obj1 = new InstanceInner();
        StaticInner obj2 = new StaticInner();

        // 굳이 접근하려면 객체를 생성해서 접근
        p7_32 outer = new p7_32();
        InstanceInner obj1 = outer.new InstanceInner();
    }
    void instanceMethod() {
        // instance 메서드에서는 인스턴스 멤버와 static 멤버 모두 접근 가능
        InstanceInner obj1 = new InstanceInner();
        StaticInner obj2 = new StaticInner();

        // 메서드 내의 지역 내부 클래스는 접근 불가
//        LocalInner lv = new LocalInner();
    }

    void myMethod() {
        class LocalInner {}
        LocalInner lv = new LocalInner();
    }
}
