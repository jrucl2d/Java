package reactive;

import java.util.concurrent.Flow;

// TempInfo 를 다른 TempInfo 로 변환하는 프로세서
public class TempProcessor implements Flow.Processor<TempInfo, TempInfo> {
    private Flow.Subscriber<? super TempInfo> subscriber;

    @Override
    public void subscribe(Flow.Subscriber<? super TempInfo> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(TempInfo item) {
        // 섭씨로 변환한 다음 TempInfo 다시 전송
        subscriber.onNext(new TempInfo(item.getTown(), (item.getTemp() - 32) * 5 / 9));
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
