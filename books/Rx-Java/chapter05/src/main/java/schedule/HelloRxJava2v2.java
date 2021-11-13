package schedule;

import io.reactivex.Observable;
import utils.Log;

public class HelloRxJava2v2 {
    public static void main(String[] args) {
        Observable.just("Hello", "RxJava 2!!")
                .subscribe(Log::i);
    }
}
