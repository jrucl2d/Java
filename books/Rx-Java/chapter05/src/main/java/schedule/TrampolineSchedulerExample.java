package schedule;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.CommonUtils;
import utils.Log;

public class TrampolineSchedulerExample {
    public static void main(String[] args) {
        // 트램펄린 스케줄러 : 처음에 지정한 스레드로 구독자에게 데이터 발행
        String[] orgs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(orgs);

        // 구독 #1
        source.map(item -> "<<" + item + ">>")
                .subscribeOn(Schedulers.trampoline()) // 계산 스케줄러 사용
                .subscribe(Log::i);

        // 구독 #2
        source.map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.trampoline())
                .subscribe(Log::i);

        CommonUtils.sleep(500);
    }
}
