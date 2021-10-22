package reactive;

import java.util.concurrent.Flow;

public class Main {

    public static void main(String[] args) {
        getTemperatures("New York").subscribe(new TempSubscriber());
    }

    // 구독한 subscriber 에게 TempSubscription 을 전송하는 publisher 반환
    private static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(
                new TempSubscription(subscriber, town)
        );
    }
}
