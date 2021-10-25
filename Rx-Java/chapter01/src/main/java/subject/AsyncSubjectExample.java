package subject;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectExample {
    public static void main(String[] args) {
        // AsyncSubject -> 가장 마지막 데이터만 발행
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("5");
        subject.onComplete();

        System.out.println("----------------");

        // 구독자로 동작하는 AsyncSubject
        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject1 = AsyncSubject.create();
        subject1.subscribe(data -> System.out.println("Subscriber #1 => " + data));

        source.subscribe(subject1);

        System.out.println("----------------");

        AsyncSubject<String> subject2 = AsyncSubject.create();
        subject2.onNext("10");
        subject2.onNext("11");
        subject2.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject2.onNext("12");
        subject2.onComplete(); // onComplete 이후의 onNext 는 무시
        subject2.onNext("13");
        subject2.subscribe(data -> System.out.println("Subscriber #2 => " + data)); // 12
        subject2.subscribe(data -> System.out.println("Subscriber #3 => " + data)); // 12
        subject2.onComplete();
    }
}
