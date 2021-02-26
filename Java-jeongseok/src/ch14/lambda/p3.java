package ch14.lambda;

class Outer {
    int val = 10; // Outer.this.val

    class Inner {
        int val = 20; // this.val

        void method(int i){
            int val = 30;
//            i = 100; // 상수의 값을 변경할 수 없다.
            MyFunction f = () -> {
//                val =3; // val 또한 변경 불가
                System.out.println("i = " + i);
                System.out.println("val = " + val);
                System.out.println("this.val = " + ++this.val); // 변경 가능
                System.out.println("Outer.this.val = " + ++Outer.this.val); // 변경 가능
            };
            f.run();
        }
    }
}
public class p3 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}
