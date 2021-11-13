package flowcontrol;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class WindowExample {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        // 시간 측정용
        CommonUtils.exampleStart();

        // 앞의 3개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.fromArray(data).take(3)
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 다음 1 개는 300ms 후에 발행
        Observable<String> middleSource = Observable.just(data[3])
            .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 마지막 데이터는 2개는 100ms 이후에 발행
        Observable<String> lastSource = Observable.just(data[4], data[5])
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable.concat(earlySource, middleSource, lastSource)
            .window(3) // 3개마다 새로운 Observable 생성
            .subscribe(stringObservable -> {
                Log.it("New observable Started!!");
                stringObservable.subscribe(Log::it);
            });

        CommonUtils.sleep(1000);
    }
}
