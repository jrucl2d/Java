package subject;

import io.reactivex.subjects.PublishSubject;

public class PublishSubjectExample {
    public static void main(String[] args) {
        // 가장 기본적인 작동. 데이터 발행하면 그것만 받음
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("5");
        subject.onComplete();
    }
}
