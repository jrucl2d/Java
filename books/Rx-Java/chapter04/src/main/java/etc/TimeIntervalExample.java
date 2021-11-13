package etc;

import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;
import utils.CommonUtils;
import utils.Log;

public class TimeIntervalExample {
    public static void main(String[] args) {
        String[] data = {"1", "3", "7"};

        // Timed 에 이전 값을 발행한 이후 얼마나 시간이 흘렀는지 알려줌
        CommonUtils.exampleStart();
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item -> {
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }
}
