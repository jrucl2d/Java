package schedule;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;
import utils.Shape;

public class FlipExample {
    public static void main(String[] args) {
        // 데이터 흐름이 발생하는 스레드와 처리된 결과를 구독자에게 전달하는 스레드를 분리할 수 있다.
        String[] objs = {"1-S", "2-T", "3-P"};
        Observable.fromArray(objs)
                .doOnNext(data -> Log.i("Original data = " + data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread()) // 여기 지정하지 않으면 subscribeOn 에서 지정한 스레드에서 모든 로직 실행
                .map(Shape::flip)
                .subscribe(Log::i); // 최초의 데이터 흐름 발생 스레드와 flip 후 스레드가 다름
        CommonUtils.sleep(500);
    }
}
