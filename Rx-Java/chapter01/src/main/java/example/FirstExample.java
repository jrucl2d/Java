package example;


import io.reactivex.Observable;

public class FirstExample {
    public void emit() {
        // Observable : 데이터의 변화가 발생하는 데이터 소스 -> 인자로는 최대 10개까지, 같은 타입 데이터만
        // just : 가장 단순한 Observable 선언 방식
        // subscribe : Observable 을 구독 subscribe 호출해야 변화한 데이터를 구독자에게 발행
        Observable.just(1, 2, 3, 4, 5, 63)
            .subscribe(System.out::println);
    }

    public static void main(String args[]) {
        FirstExample demo = new FirstExample();
        demo.emit();
    }
}
