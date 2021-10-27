package create;

import io.reactivex.Observable;
import utils.Log;

public class RepeatExample {
    public static void main(String[] args) {
        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
            .repeat(3) // 3번 반복
            .doOnComplete(() -> Log.i("onComplete"))
            .subscribe(Log::i);
    }
}
