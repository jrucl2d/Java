package etc;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import utils.Log;

public class MathFunctionsExample {
    public static void main(String[] args) {
        Integer[] data = {1, 2, 3, 4};

        // 1. count
        Single<Long> source = Observable.fromArray(data)
                .count();
        source.subscribe(count -> Log.i("count is " + count));

        // 2. max() & min()
        Flowable.fromArray(data)
                .to(MathFlowable::max)
                .subscribe(Log::i);

        Flowable.fromArray(data)
                .to(MathFlowable::min)
                .subscribe(Log::i);

        // 3. sum() & average
        Flowable.fromArray(data)
                .to(MathFlowable::sumInt)
                .subscribe(Log::i);

        Observable.fromArray(data)
                .toFlowable(BackpressureStrategy.BUFFER) // Flowable 생성 시의 배압 전략의 기본값
                .to(MathFlowable::averageDouble)
                .subscribe(Log::i);
    }
}
