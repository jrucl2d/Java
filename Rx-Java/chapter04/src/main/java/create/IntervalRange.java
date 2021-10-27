package create;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class IntervalRange {
    public static void main(String[] args) {
        // interval + range
        Observable.intervalRange(1,
            5,
            100L,
            100L,
            TimeUnit.MILLISECONDS)
            .subscribe(Log::i);
        CommonUtils.sleep(1000);

        // 직접 조합해서 만들어보기
        Observable.interval(100L, 100L, TimeUnit.MILLISECONDS)
            .map(i -> i + 1)
            .take(5)
            .subscribe(Log::i);
        CommonUtils.sleep(1000);
    }
}
