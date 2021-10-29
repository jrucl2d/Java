package conditional;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class SkipUntilExample {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        // other 발행 이후부터 발행됨 -> 5만 발행될 때도 있고 5, 6이 발행될 때가 있는 듯.
        Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS)) // 500ms 뒤에 발행되는 other
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
    }
}
