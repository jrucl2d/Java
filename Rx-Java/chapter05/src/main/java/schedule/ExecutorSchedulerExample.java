package schedule;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorSchedulerExample {
    public static void main(String[] args) {
        final int THREAD_NUM = 10;

        String[] data = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(data);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);

        source.subscribeOn(Schedulers.from(executor))
                .subscribe(Log::i);
        source.subscribeOn(Schedulers.from(executor))
                .subscribe(Log::i);
        CommonUtils.sleep(500);
    }
}
