package operators;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class MapExample {
    public static void main(String[] args) {
        // map -> @CheckReturnValue : 반환값을 확인
        // @SchedulerSupport(value="none") : 스케줄러 지원 X(현재 스레드에서 실행)
        String[] balls = {"1", "2", "3", "4"};
        Observable<String> source = Observable.fromArray(balls)
            .map(ball -> "<" + ball + ">");
        source.subscribe(System.out::println);

        Function<String, String> getDiamond = ball -> "<" + ball + ">";

        Observable<String> source1 = Observable.fromArray(balls)
            .map(getDiamond);
        source1.subscribe(System.out::println);

        Function<String, Integer> ballToIndex = ball -> {
            switch (ball) {
                case "RED": return 1;
                case "YELLOW": return 2;
                case "GREEN": return 3;
                case "BLUE": return 4;
                default: return -1;
            }
        };

        String[] balls2 = {"RED", "YELLOW", "GREEN", "BLUE"};
        Observable<Integer> source2 = Observable.fromArray(balls2)
            .map(ballToIndex);
        source2.subscribe(System.out::println);
    }
}
