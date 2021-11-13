package operators;

import io.reactivex.Observable;
import io.reactivex.Single;

public class FilterExample {
    public static void main(String[] args)
    {
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
        Observable.fromArray(objs)
            .filter(obj -> obj.endsWith("CIRCLE")) // Predicate 를 인자로 받음
            .subscribe(System.out::println);

        Integer[] data = {100, 34, 27, 99, 50};
        Observable.fromArray(data)
            .filter(number -> number % 2 == 0)
            .subscribe(System.out::println);
        System.out.println("-------------------");

        // 1. first : 첫 번째 값을 필터. 값없이 완료되면 기본값 리턴
        Observable.fromArray(data).first(-1)
            .subscribe(System.out::println);

        // 2. last : 마지막 값을 필터. 값없이 완료되면 기본값 리턴
        Observable.fromArray(data).last(999)
            .subscribe(System.out::println);

        // 3. take : 최초 N 개만 가져옴
        Observable.fromArray(data).take(3)
            .subscribe(System.out::println);

        // 4. takeLast : 마지막 N 개만 필터함
        Observable.fromArray(data).takeLast(3)
            .subscribe(System.out::println);
        // 5. skip : 최초 N 개 값을 건너뜀
        Observable.fromArray(data).skip(3)
            .subscribe(System.out::println);

        // 6. skipLast : 마지막 N 개 값을 건너 뜀
        Observable.fromArray(data).skipLast(3)
            .subscribe(System.out::println);
    }
}
