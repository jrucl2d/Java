package flowcontrol;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class BufferExample
{
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        // 앞의 3개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.fromArray(data)
            .take(3)
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 가운데 1개는 300ms 후에 발행
        Observable<String> middleSource = Observable.just(data[3])
            .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 마지막 데이터 2개는 100ms 이후에 발행
        Observable<String> lastSource = Observable.just(data[4], data[5])
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 세 개씩 모아서 한꺼번에 발행
        Observable.concat(earlySource, middleSource, lastSource)
//            .buffer(3) // 3개씩 묶어서 발행
            .buffer(2, 3) // count, skip 으로 count 개 모으고 skip - count 개는 스킵
            .subscribe(Log::it);

        CommonUtils.sleep(1000);
    }
}
