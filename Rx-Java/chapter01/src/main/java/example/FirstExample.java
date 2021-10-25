package example;


import io.reactivex.Observable;

public class FirstExample {
    public void emit() {
        // Observable : 데이터의 변화가 발생하는 데이터 소스
        // just : 가장 단순한 Observable 선언 방식
        // subscribe : Observable 을 구독 subscribe 호출해야 변화한 데이터를 구독자에게 발행
        Observable.just("Hello", "RxJava 2!!")
            .subscribe(System.out::println);
    }

    public static void main(String args[]) {
        FirstExample demo = new FirstExample();
        demo.emit();
    }
}
