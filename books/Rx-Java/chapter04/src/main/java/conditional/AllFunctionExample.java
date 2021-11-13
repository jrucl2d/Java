package conditional;

import io.reactivex.Observable;
import utils.CommonUtils;
import utils.Log;
import utils.Shape;

import java.util.concurrent.TimeUnit;

public class AllFunctionExample {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4"};

        Observable.fromArray(data)
                .map(Shape::getSuffix)
                .all(Shape.BALL::equals)
                .subscribe(Log::i);
    }
}
