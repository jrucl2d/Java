package ch14.lambda;

public class p2 {
    public static void main(String[] args) {
        MyFunction f1 = () -> {};
        Object obj = (MyFunction) (() -> {});
        String str = ((Object)(MyFunction) (()-> {})).toString();

        System.out.println("f1 = " + f1);
        System.out.println("obj = " + obj);
        System.out.println("str = " + str);
//        System.out.println(() -> {}); // 람다식은 Object 타입으로 형변환 불가
        System.out.println((MyFunction) (() -> {}));
//        System.out.println((MyFunction)(()-> {}).toString()); // 에러
        System.out.println(((Object)(MyFunction)(()->{})).toString());
    }
}
