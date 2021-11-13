package flowcontrol;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class ThrottleFirstExample {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        // 시간 측정용
        CommonUtils.exampleStart();

        // 앞의 1 개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.just(data[0])
            .zipWith(Observable.timer(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 다음 1 개는 300ms 후에 발행
        Observable<String> middleSource = Observable.just(data[1])
            .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 마지막 데이터는 4개는 100ms 이후에 발행
        Observable<String> lastSource = Observable.fromArray(data)
            .skip(2)
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
//            .doOnNext(Log::it);

        Observable.concat(earlySource, middleSource, lastSource)
            .throttleFirst(200L, TimeUnit.MILLISECONDS) // 지정된 시간동안 발행하지 않음
            .subscribe(Log::it);

        CommonUtils.sleep(1000);
    }
}
