package ch14.lambda;

@FunctionalInterface
interface MyFunction {
    void run(); // public abstract void run();
}
public class p1 {
    static void execute(MyFunction f){ // 매개변수 타입이 MyFunction인 메서드
        f.run();
    }
    static MyFunction getMyFunction() { // 반환 타입이 MyFunction인 메서드
        MyFunction f = () -> System.out.println("p1.getMyFunction");
        return f;
    }
    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run()을 구현함
        MyFunction f1 = () -> System.out.println("p1.f1.run()");
        MyFunction f2 = new MyFunction() { // 익명 클래스로 run() 구현
            @Override
            public void run() { // public 필수
                System.out.println("p1.f2.run");
            }
        };
        MyFunction f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();
        execute(f1); // 매개변수로 람다식 참조 변수
        execute(() -> System.out.println("run()")); // 매개변수로 람다식 자체
    }
}
