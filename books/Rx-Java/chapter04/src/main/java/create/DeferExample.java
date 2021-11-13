package create;

import io.reactivex.Observable;
import utils.Log;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class DeferExample {
    static Iterator<String> colors = Arrays.asList("1", "3", "5", "6").iterator();

    public static void main(String[] args) {
        Callable<Observable<String>> supplier = DeferExample::getObservable;
        Observable<String> source = Observable.defer(supplier);
        source.subscribe(val -> Log.i("Subscriber #1 : " + val)); // subscribe 할 때 Observable 생성
        source.subscribe(val -> Log.i("Subscriber #2 : " + val));

        // 차가운 Observable 이기 때문에 Observable 생성할 때 입력값이 결정되고 subscribe 하면 항상 같은 값이 리턴됨. 여기선 5
        Observable<String> source2 = getObservable();
        source2.subscribe(val -> Log.i("Subscriber #1 : " + val));
        source2.subscribe(val -> Log.i("Subscriber #2 : " + val));
    }

    private static Observable<String> getObservable() {
        if (colors.hasNext()) {
            String color = colors.next();
            return Observable.just(
                color + "-ball",
                color + "-rectangle",
                color + "-pentagon"
            );
        }
        return Observable.empty();
    }
}
