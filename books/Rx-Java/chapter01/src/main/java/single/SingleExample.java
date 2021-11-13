package single;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleExample {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Hello Single");

        // Observable -> Single
        Single.fromObservable(source)
            .subscribe(System.out::println);

        // single 함수 호출
        Observable.just("Hello Single")
            .single("default item")
            .subscribe(System.out::println);

        // first 함수로 Single 생성
        String[] colors = {"Red", "Blue"};
        Observable.fromArray(colors)
            .first("default value")
            .subscribe(System.out::println);

        // empty Observable 에서 Single 생성
        Observable.empty()
            .single("default value")
            .subscribe(System.out::println);

        // take 함수에서 Single 생성
        Observable.just("aa", "bb")
            .take(1)
            .single("default value")
            .subscribe(System.out::println);

        // 에러 발생 케이스 -> IllegalArgumentException
        Single<String> source1 = Observable
            .just("Hello Single", "Error").single("default");

        source1.subscribe(System.out::println);
    }
}
