package create;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerExample {
    public static void main(String[] args) {
        // 딱 한 번 실행되는 Interval 이라고 할 수 있음. 0L이 리턴 됨.
        CommonUtils.exampleStart();
        Observable.timer(500L, TimeUnit.MILLISECONDS)
            .map(notUsed -> {
                return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .format(new Date());
            }).subscribe(Log::it);
        CommonUtils.sleep(1000);
    }
}
