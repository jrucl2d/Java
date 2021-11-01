package debugging;

import io.reactivex.Observable;
import utils.Log;

public class DoOnExample {
    public static void main(String[] args) {
        String[] orgs = {"1", "3", "5"};
        Observable.fromArray(orgs)
            .doOnNext(data -> Log.i("onNext() : " + data))
            .doOnComplete(() -> Log.i("onComplete()"))
            .doOnError(e -> Log.e("onError()", e.getMessage()))
            .subscribe(Log::i);

        // 0으로는 나눌 수 없음
        // onErrorNotImplementedException 발생함
        Integer[] divider = {10, 5, 0};
        Observable.fromArray(divider)
            .map(div -> 1000 / div)
            .doOnNext(data -> Log.i("onNext() : " + data))
            .doOnComplete(() -> Log.i("onComplete()"))
            .doOnError(e -> Log.e("onError()", e.getMessage()))
            .subscribe(Log::i);
    }
}
