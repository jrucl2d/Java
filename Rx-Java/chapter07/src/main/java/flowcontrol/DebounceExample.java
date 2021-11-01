package flowcontrol;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.TimeUnit;

public class DebounceExample {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "5"};

        Observable.concat(
            Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[0]),
            Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[1]),
            Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[2]),
            Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[3])
        ).debounce(200L, TimeUnit.MILLISECONDS) // 시간 간격 안에 들어오고 다른 이벤트 없으면 그대로 발행. 시간 간격 안에 다른 이벤트 들어오면 해당 이벤트만 발행.
            .subscribe(Log::i);
        CommonUtils.sleep(1000);
    }
}
