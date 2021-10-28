package combine;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;
import utils.Shape;

import java.util.concurrent.TimeUnit;

public class ZipExample {
    public static void main(String[] args) {
        String[] shapes = {"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};

        Observable.zip(
            Observable.fromArray(shapes).map(Shape::getSuffix), // 모양 가져옴
            Observable.fromArray(coloredTriangles).map(Shape::getColor), // 색상 가져옴
            (suffix, color) -> color + suffix
        ).subscribe(Log::i);

        Observable.zip(
            Observable.just(100, 200, 300),
            Observable.just(10, 20, 30),
            Observable.just(1, 2, 3),
            (a, b, c) -> a + b + c
        ).subscribe(Log::i);

        // zipInterval 기법
        Observable<String> source = Observable.zip(
            Observable.just("RED", "GREEN", "BLUE"),
            Observable.interval(200L, TimeUnit.MILLISECONDS), // 200ms 마다 발행되는 값과 결합
            (val, i) -> val
        );
        CommonUtils.exampleStart();
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }
}
