package flowable;

import io.reactivex.Observable;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Log;

import java.util.concurrent.TimeUnit;

class TestAsyncExample {
    @DisplayName("test Observable.interval() wrong")
    @Test
    @Disabled // 이 테스트는 정상적으로 동작 X. interval 은 계산 스케줄러에서 실행되므로.
    void testIntervalWrongWay() {
        Observable<Integer> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
            .take(5)
            .map(Long::intValue);

        source.doOnNext(Log::i)
            .test().assertResult(0, 1, 2, 3, 4);
    }

    @DisplayName("test Observable.interval() right")
    @Test
    void testInterval() {
        Observable<Integer> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
            .take(5)
            .map(Long::intValue);

        source.doOnNext(Log::i)
            .test()
            .awaitDone(1L, TimeUnit.SECONDS) // 1초 동안 기다림
            .assertResult(0, 1, 2, 3, 4);
    }
}
