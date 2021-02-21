package ch07.before;

public class p7_14 {
    public static void main(String[] args) {
        //    Singleton singleton = new Singleton();
        Singleton singleton = Singleton.getInstance();
    }
}

// private 생성자를 자손이 접근할 수 없으므로 상속 불가. final로 알리자.
final class Singleton {
    private static Singleton singleton = new Singleton();

    // final과 private은 오버라이딩이 불가능하다는 의미를 공유하므로 하나만 적자.
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}