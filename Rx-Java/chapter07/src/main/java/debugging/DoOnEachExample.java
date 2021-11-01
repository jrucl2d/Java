package debugging;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import utils.Log;

public class DoOnEachExample {
    public static void main(String[] args) {
        String[] data = {"ONE", "TWO", "THREE"};
        Observable<String> source = Observable.fromArray(data);
        source
            .doOnEach(noti -> {
                if (noti.isOnNext()) Log.i("onNext() : " + noti.getValue());
                if (noti.isOnComplete()) Log.i("onComplete()");
                if (noti.isOnError()) Log.e("onError()", noti.getError().getMessage());
            });

        source.subscribe(Log::i);

        // 위와 같음, 그러나 자주 사용하는 방법은 아님
        Observable<String> source1 = Observable.fromArray(data);
        source1.doOnEach(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                // doOnEach()에서는 onSubscribe() 함수 호출되지 않음
                // doOnEach()에서는 onNext, onError, onComplete 이벤트만 처리함
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i("onNext() : " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("onError()", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("onComplete()");
            }
        }).subscribe(Log::i);
    }
}
