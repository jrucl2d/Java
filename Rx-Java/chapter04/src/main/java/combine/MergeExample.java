package combine;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;
import utils.Shape;

import java.util.concurrent.TimeUnit;

public class MergeExample {
    public static void main(String[] args) {
        String[] data1 = {"1", "3"};
        String[] data2 = {"2", "4", "6"};

        Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data1[idx])
                .take(data1.length);

        Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length);

        // 단순히 합침, 두 Observable 의 데이터 발행이 모두 개별의 스레드에서 이루어짐
        Observable<String> source = Observable.merge(source1, source2);

        source.subscribe(Log::i);

        CommonUtils.sleep(1000);
    }
}
