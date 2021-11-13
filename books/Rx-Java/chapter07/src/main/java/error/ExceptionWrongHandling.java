package error;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import utils.Log;

public class ExceptionWrongHandling
{
    public static void main(String[] args) {
        Observable<String> source = Observable.create(
            (ObservableEmitter<String> emitter) -> {
                emitter.onNext("1");
                emitter.onError(new Exception("Some Error"));
                emitter.onNext("3");
                emitter.onComplete();
            }
        );

        try {
            source.subscribe(Log::i);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }
}
