package create;

import io.reactivex.Observable;
import utils.Log;

public class RangeExample {
    public static void main(String[] args) {
        // range 는 현재 스레드에서 실행됨
        Observable.range(1, 10)
            .filter(n -> n % 2 == 0)
            .subscribe(Log::i);
    }
}
