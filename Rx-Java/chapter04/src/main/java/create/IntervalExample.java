package create;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class IntervalExample {
    public static void main(String[] args) {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
            .map(data -> (data + 1) * 100)
            .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000); // RxComputationThreadPool-1 스레드를 main 스레드가 기다려야 하므로
    }
}
