package flowable;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class FlowableExample {
    public static void main(String[] args) throws InterruptedException
    {
        Flowable.just("Hello world")
            .subscribe(System.out::println);

        Flowable.fromCallable(() -> {
            Thread.sleep(1000); // 값비싼 연산을 흉내냄
            return "Done";
        })
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000);
    }
}
