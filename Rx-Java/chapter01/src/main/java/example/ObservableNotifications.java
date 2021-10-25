package example;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ObservableNotifications {
    private static Observable<String> source = Observable.just("RED", "GREEN", "YELLOW");

    public static void main(String[] args) {
        // Disposable : Subscriptioin(구독) 객체에 해당하는 정보. 이를 활용해 Observable 로 작성한 코드를 테스트 가능
        Disposable d = source.subscribe(
            v -> System.out.println("onNext() : value " + v),
            err -> System.err.println("onError() : err " + err.getMessage()),
            () -> System.out.println("onComplete()")
        );
        System.out.println("isDisposed() : " + d.isDisposed());
    }
}
