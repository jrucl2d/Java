package transform;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class ConcatMapExample {
    public static void main(String[] args) {
        CommonUtils.exampleStart();
        // concatMap -> 나중에 생긴 데이터가 끼어들 수 없다. 순서 보장. (interleaving 보장)
        String[] balls = {"1", "3", "5"};
        Observable.interval(100L, TimeUnit.MILLISECONDS)
            .map(Long::intValue)
            .map(index -> balls[index])
            .take(balls.length)
            .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map(notUsed -> ball + "<>")
                .take(2)
            ).subscribe(Log::it);
        CommonUtils.sleep(2000);
    }
}
