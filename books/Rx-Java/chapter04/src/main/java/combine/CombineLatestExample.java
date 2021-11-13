package combine;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;
import utils.Shape;

import java.util.concurrent.TimeUnit;

public class CombineLatestExample {
    public static void main(String[] args) {
        // 개수가 달라도 됨
        String[] data1 = {"6", "7", "4", "2"};
        String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};

        Observable.combineLatest(
            Observable.fromArray(data1)
                            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                                    (shape, notUsed) -> Shape.getColor(shape)),
                Observable.fromArray(data2)
                        .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getSuffix(shape)), (v1, v2) -> v1 + v2
        ).subscribe(Log::i);

        CommonUtils.sleep(1000);
    }
}
