package conditional;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TakeUntilExample {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        // other 이 발행되면 멈춤
        Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS)) // 500ms 뒤에 발행되는 other
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
    }
}
