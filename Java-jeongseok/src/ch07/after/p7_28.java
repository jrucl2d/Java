package ch07.after;

class A {
    void autoPlay(I i){
        i.play();
    }
}
interface I {
    public abstract void play();
}
class B implements I {
    @Override
    public void play() {
        System.out.println("B.play");
    }
}
class C implements I {
    @Override
    public void play() {
        System.out.println("C.play");
    }
}
public class p7_28 {
    public static void main(String[] args) {
        A a = new A();
        a.autoPlay(new B());
        a.autoPlay(new C());
    }
}
