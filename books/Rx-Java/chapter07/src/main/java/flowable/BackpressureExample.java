package flowable;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import utils.CommonUtils;
import utils.Log;

public class BackpressureExample {
    public static void main(String[] args) {
        CommonUtils.exampleStart();

        // 백프레셔가 필요한 상황
//        PublishSubject<Integer> subject = PublishSubject.create();
//        subject.observeOn(Schedulers.computation())
//            .subscribe(data -> {
//                CommonUtils.sleep(100); // 100ms 후 데이터 처리
//                Log.it(data);
//            }, err -> Log.e("에러 ", err.toString()));
//
//        for (int i = 0; i < 50_000_000; i++) {
//            subject.onNext(i);
//        }
//        subject.onComplete();

        // Flowable의 onBackpressureBuffer 사용
//        Flowable.range(1, 50_000_000)
//            .onBackpressureBuffer(128, () -> {}, BackpressureOverflowStrategy.DROP_LATEST)
//            .observeOn(Schedulers.computation())
//            .subscribe(data -> {
//                CommonUtils.sleep(100);
//                Log.it(data);
//            }, err -> Log.e("에러", err.getMessage()));

        // onBackpressureDrop -> 버퍼 개수인 128개 만큼 보관하고 나머지 버리므로 128까지 출력됨
//        Flowable.range(1, 50_000_000)
//            .onBackpressureDrop()
//            .observeOn(Schedulers.computation())
//            .subscribe(data -> {
//                CommonUtils.sleep(100);
//                Log.it(data);
//            }, err -> Log.e("에러", err.getMessage()));
//        CommonUtils.sleep(20_000);

        // onBackpressureLatest() -> onBackpressureDrop과 동일하지만 마지막 값을 발행할 수 있게 해줌. 128, 50000000
        Flowable.range(1, 50_000_000)
            .onBackpressureLatest()
            .observeOn(Schedulers.computation())
            .subscribe(data -> {
                CommonUtils.sleep(100);
                Log.it(data);
            }, err -> Log.e("에러", err.getMessage()));
        CommonUtils.sleep(20_000);
    }
}
