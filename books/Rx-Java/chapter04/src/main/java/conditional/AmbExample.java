package conditional;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmbExample {
    public static void main(String[] args) {
        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2-R", "4-R"};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete(() -> Log.i("Observable #1 : onComplete()")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS) // delay 가 있으므로 선택되지 못함
                        .doOnComplete(() -> Log.i("Observable #2 : onComplete()"))
        );

        Observable.amb(sources) // 먼저 발행된 Observable 만 채택
                .doOnComplete(() -> Log.i("Result : onComplete()"))
                .subscribe(Log::i);
        CommonUtils.sleep(1000);
    }
}
