package connectable;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class ConnectableObservableExample {
    public static void main(String[] args) throws InterruptedException
    {
        String[] dt = {"1", "3", "5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
            .map(Long::intValue)
            .map(i -> dt[i])
            .take(dt.length);
        ConnectableObservable<String> source = balls.publish(); // connect 함수 호출 전까지 데이터 발행을 유예git
        source.subscribe(data -> System.out.println("Subscriber #1 => " + data)); // 호출해도 아무 동작도 발생 X
        source.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        source.connect(); // connect 함수를 호출한 시점부터 subscribe 한 구독자에게 데이터 발행을 시작

        Thread.sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 => " + data)); // connect 호출 이후 굳ㄱ하면 바로 수신
        Thread.sleep(100);
        // 이후 세 구독자 모두 구독 해지
    }
}
