package examples;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;

public class CallbackHeaven {
    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = "https://github.com/samples/callback_hell";

    public static void main(String[] args) {
        CommonUtils.exampleStart();
        Observable<String> first = Observable.just(FIRST_URL)
                        .subscribeOn(Schedulers.io())
                        .map(OkHttpClient::get);

        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpClient::get);

        Observable.zip(first, second, (a, b) -> ("\n>> " + a + "\n>>" + b))
                        .subscribe(Log::it);
        CommonUtils.sleep(5000);
    }
}
