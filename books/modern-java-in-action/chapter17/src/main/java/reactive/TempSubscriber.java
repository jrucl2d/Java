package reactive;

import java.util.concurrent.Flow;

public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); // 구독을 저장하고 첫 번째 요청 전달
    }

    @Override
    public void onNext(TempInfo tempInfo) {
        System.out.println(tempInfo); // 수신한 온도 출력하고 다음 정보 요청
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("DONE!");
    }
}
