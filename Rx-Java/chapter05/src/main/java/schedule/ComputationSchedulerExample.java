package schedule;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class ComputationSchedulerExample {
    public static void main(String[] args) {
        String[] orgs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 구독 #1
        source.map(item -> "<<" + item + ">>")
                .subscribeOn(Schedulers.computation()) // 계산 스케줄러 사용
                .subscribe(Log::i);

        // 구독 #2
        source.map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.computation())
                .subscribe(Log::i);

        // 구독 1과 구독 2가 거의 동시에 이루어지므로 RxJava 내부에서 동일한 스레드에 작업을 할당할 수도 있다.
        // 실행 결과에 ThreadPool-3, ThreadPool-4 두 개가 사용될 수도, 3만 사용될 수도

        CommonUtils.sleep(1000);
    }
}
