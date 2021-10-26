package operators;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class FlatMapExample {
    public static void main(String[] args) {
        // 가장 먼저 함수를 별도로 정의하는 것이 중요
        Function<String, Observable<String>> getDoubleDiamonds =
            ball -> Observable.just(ball + "<>", ball + "<>");

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
            .flatMap(getDoubleDiamonds);
        source.subscribe(System.out::println);

        // 람다를 사용한 경우
        Observable.fromArray(balls)
            .flatMap(ball -> Observable.just(ball + "<>", ball + "<>"))
            .subscribe(System.out::println);
    }
}
