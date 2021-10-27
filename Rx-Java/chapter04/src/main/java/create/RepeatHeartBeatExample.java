package create;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RepeatHeartBeatExample {
    public static void main(String[] args) {
        CommonUtils.exampleStart();

        String serverUrl = "https://api.github.com/zen";

        Observable.timer(2, TimeUnit.SECONDS) // 2초 간격 ping
            .map(val -> serverUrl)
            .map(OkHttpHelper::get)
            .repeat() // 동작이 한 번 끝나면 다시 구독함
            .subscribe(res -> Log.it("Ping Result : " + res));
        CommonUtils.sleep(10000);
    }

    public static class OkHttpHelper {
        private static OkHttpHelper client = new OkHttpHelper();

        public static String get(String url) throws IOException {
            return "haha";
        }
    }
}
