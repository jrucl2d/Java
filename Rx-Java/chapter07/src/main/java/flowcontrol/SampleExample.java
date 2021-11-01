package flowcontrol;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class SampleExample {
    public static void main(String[] args) {
        String[] data = {"1", "7", "2", "3", "6"};

        // 시간 측정용
        CommonUtils.exampleStart();

        // 앞의 4 개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.fromArray(data)
            .take(4)
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 마지막 데이터는 300ms 이후에 발행
        Observable<String> lastSource = Observable.just(data[4])
            .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 두 개의 Observable 결합하고 300ms 로 샘플링
        Observable.concat(earlySource, lastSource)
            .sample(300L, TimeUnit.MILLISECONDS, true) // 이 시점 기준 마지막 데이터를 발행
            .subscribe(Log::it);

        // sample 마지막 값을 true 로 준다면 sample 함수 끝나지 않았는데 Observable 이 종료되는 경우 마지막 값이 발행됨

        CommonUtils.sleep(1000);
    }
}
