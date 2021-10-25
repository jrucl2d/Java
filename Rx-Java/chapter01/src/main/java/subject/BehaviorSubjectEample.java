package subject;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectEample {
    public static void main(String[] args) {
        // 이전의 가장 최신이 없으면 기본값을 받고, 있으면 최근값 발행받고 그 다음부터는 정상 진행
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
        subject.onNext("5");
        subject.onComplete();
    }
}
