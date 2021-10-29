package combine;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class ConcatExample {
    public static void main(String[] args) {
        Action onCompleteAction = () -> Log.i("onComplete()");
        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2", "4", "6"};

        Observable<String> source1 = Observable.fromArray(data1)
                .doOnComplete(onCompleteAction); // source1이 무조건 onComplete 이벤트가 발생해야 한다! 안 그러면 메모리 누수

        Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length)
                .doOnComplete(onCompleteAction);

        Observable<String> source = Observable.concat(source1, source2)
                        .doOnComplete(onCompleteAction);

        source.subscribe(Log::i);

        CommonUtils.sleep(1000);
    }
}
