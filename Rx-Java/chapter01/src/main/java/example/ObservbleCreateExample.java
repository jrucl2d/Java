package example;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ObservbleCreateExample {
    public static void main(String[] args) {
        // create : 직접 onNext, onComplete, onError 해줘야 함
        // create 메소드는 주의해서 사용해야 함
        Observable<Integer> source = Observable.create(
            (ObservableEmitter<Integer> emitter) -> {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
                emitter.onComplete();
            }
        );
        source.subscribe(System.out::println);
    }
}
