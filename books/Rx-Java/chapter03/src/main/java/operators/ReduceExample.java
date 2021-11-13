package operators;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class ReduceExample {
    public static void main(String[] args) {
        // reduce 는 리턴 값이 없을 수 있으므로 리턴 결과가 Maybe 여야 한다.
        String[] balls = {"1", "3", "5"};
        Maybe<String> source = Observable.fromArray(balls)
            .reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(System.out::println);
    }
}
