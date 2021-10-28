package transform;

import io.reactivex.Observable;
import utils.Log;

public class ScanExample {
    public static void main(String[] args) {
        // reduce -> 모든 데이터가 입력된 후 종합하여 1개 데이터만 발행 (Maybe 발행)
        // scan -> 실행 때마다 입력값에 맞는 중간 결과 및 최종 결과 발행 (Observable 발행)
        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
            .scan((b1, b2) -> b2 + "(" + b1 + ")")
            .subscribe(Log::i);
    }
}
