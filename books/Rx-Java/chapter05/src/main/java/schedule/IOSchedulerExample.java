package schedule;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;

import java.io.File;

public class IOSchedulerExample {
    public static void main(String[] args) {
        // CPU 개수만큼 스레드 생성하는 계산 스케줄러와 다르게 필요할 때마다 스레드 계속 생성하는 IO 스케줄러
        String root = "/";
        File[] files = new File(root).listFiles();
        Observable.fromArray(files)
                .filter(f -> !f.isDirectory())
                .map(f -> f.getAbsolutePath())
                .subscribeOn(Schedulers.io())
                .subscribe(Log::i);

        CommonUtils.sleep(500);
    }
}
