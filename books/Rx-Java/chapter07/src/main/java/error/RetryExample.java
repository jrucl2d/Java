package error;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RetryExample {
    public static void main(String[] args) {
        CommonUtils.exampleStart();

//        Observable.just("1", "3", "A5")
//            .map(Integer::parseInt)
//            .retry((retryCnt, e) -> {
//                Log.i("retryCnt = " + retryCnt);
//                CommonUtils.sleep(1000);
//
//                return retryCnt < 5 ? true : false;
//            })
//            .onErrorReturn(e -> -1)
//            .subscribe(Log::i);

//        Observable.just("1", "3", "A5")
//            .map(Integer::parseInt)
//            .subscribeOn(Schedulers.io())
//            .retryUntil(() -> {
//                if (new Random().nextBoolean() == true)
//                    return true; // 중지
//
//                CommonUtils.sleep(1000);
//                return false; // 계속 진행
//            })
//            .subscribe(Log::i);
//
//        CommonUtils.sleep(5000);

        Observable.create(emitter -> emitter.onError(new RuntimeException("always fail")))
            .retryWhen(attempts // Observable 객체
                -> attempts.zipWith(Observable.range(1, 3), (n, i) -> i)
                .flatMap(i -> {
                    Log.i("delay retry by " + i + " seconds");
                    return Observable.timer(i, TimeUnit.SECONDS);
                })).blockingForEach(Log::i);
    }
}
