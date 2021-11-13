package transform;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class SwitchMapExample {
    public static void main(String[] args) {
        CommonUtils.exampleStart();
        String[] balls = {"1", "3", "5"};
        Observable.interval(100L, TimeUnit.MILLISECONDS)
            .map(Long::intValue)
            .map(index -> balls[index])
            .take(balls.length)
            .doOnNext(Log::i) // ..ThreadPool-1 사용됨. 즉, 값을 발행하는 데 사용됨.
            .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map(notUsed -> ball + "<>")
                .take(2)
            ).subscribe(Log::it);
        // 5<>만 두 번 발행됨.
        // 수는 100ms 간격으로 발행되고, <>는 200ms 간격으로 발행되므로 1<> 발행 전에 5가 발행됨.
        // 그래서 switchMap 의 특성상 마지막 값인 5<>만 두 출력됨.
        CommonUtils.sleep(2000);
    }
}
