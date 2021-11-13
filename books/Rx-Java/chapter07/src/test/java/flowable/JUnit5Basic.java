package flowable;

import io.reactivex.Observable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Log;
import utils.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JUnit5Basic {

    @DisplayName("JUnit 5 First example")
    @Test
    void testFirst() {
        int expected = 3;
        int actual = 1 + 2;
        assertThat(expected).isEqualTo(actual);
    }

    @DisplayName("test getShape() Observable")
    @Test
    void testGetShapeObservable() {
        String[] data = {"1", "2-R", "3-T"};
        Observable<String> source = Observable.fromArray(data);

        String[] expected = {"1", "2-R", "3-T"};
        List<String> actual = new ArrayList<>();
        source.doOnNext(Log::i)
            .subscribe(actual::add);

        assertThat(Arrays.asList(expected)).isEqualTo(actual);
    }

    @DisplayName("#1: using TestObserver for Shape")
    @Test
    void testGetShapeUsingTestObservable() {
        String[] data = {"1", "2-R", "3-T"};
        Observable<String> source = Observable.fromArray(data);

        String[] expected = {"1", "2-R", "3-T"};
        source.test().assertResult(expected);
    }

    @DisplayName("assertFailure() example")
    @Test
    void assertFailureExample() {
        String[] data = {"100", "200", "%300"};
        Observable<Integer> source = Observable.fromArray(data)
            .map(Integer::parseInt);

        source.test().assertFailure(NumberFormatException.class, 100, 200); // 앞 두 개까지는 정상
        source.test().assertFailureAndMessage(NumberFormatException.class, "For input string: \"%300\"", 100, 200);
    }

    @DisplayName("assertComplete() example")
    @Test
    void assertComplete() {
        Observable<String> source = Observable.create(
            emitter -> {
                emitter.onNext("Hello RxJava");
                emitter.onComplete();
            }
        );
        source.test().assertComplete(); // onComplete 알람 보냈는지 확인
    }

}
