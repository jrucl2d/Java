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
        Observable<String> source = Observable.just(FIRST_URL)
                        .subscribeOn(Schedulers.io())
                        .map(OkHttpClient::get)
                        .concatWith(Observable.just(SECOND_URL) // 현재의 Observable 에 인자의 Observable 을 결합
                                .map(OkHttpClient::get));

        source.subscribe(Log::it);
        CommonUtils.sleep(5000);
    }
}
