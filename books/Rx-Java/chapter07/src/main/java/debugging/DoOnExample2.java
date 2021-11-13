package debugging;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class DoOnExample2 {
    public static void main(String[] args) {
        String[] orgs = {"1", "3", "5", "2", "6"};
        Observable<String> source = Observable.fromArray(orgs)
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
            .doOnSubscribe(d -> Log.i("onSubscribe()"))
            .doOnDispose(() -> Log.i("onDispose()"));

        Disposable d = source.subscribe(Log::i);

        CommonUtils.sleep(200);
        d.dispose();
        CommonUtils.sleep(300);

        // 위와 같은 작동
        Observable<String> source2 = Observable.fromArray(orgs)
            .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
            .doOnLifecycle(
                dd -> Log.i("onSubscirbe()"),
                () -> Log.i("onDispose()")
            );

        Disposable dd = source2.subscribe(Log::i);

        CommonUtils.sleep(200);
        dd.dispose();
        CommonUtils.sleep(300);

        Observable.fromArray(orgs)
            .doOnTerminate(() -> Log.i("onTerminate()")) // onError / onComplete 직전에 호출됨
            .doOnComplete(() -> Log.i("onComplete"))
            .doOnError(e -> Log.e("onError()", e.getMessage()))
            .subscribe(Log::i);
    }
}
