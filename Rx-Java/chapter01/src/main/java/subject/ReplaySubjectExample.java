package subject;


import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample {
    public static void main(String[] args) {
        // 이전에 발행된 모든 데이터를 구독하는 순간 모두 발행받음
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("5");
        subject.onComplete();
    }
}
