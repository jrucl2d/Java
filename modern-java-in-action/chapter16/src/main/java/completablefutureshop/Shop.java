package completablefutureshop;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product); // 다른 스레드에서 비동기적으로 수행
                futurePrice.complete(price); // 계산 완료되면 Future 에 값 설정
            } catch (Exception e) {
                futurePrice.completeExceptionally(e); // 도중에 문제가 발생하면 발생한 에러를 포함시켜 Future 종료
            }
        }).start();
        return futurePrice; // 계산 결과의 완료를 기다리지 않고 Future 반환
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // 물건 발행자 or 제조사 관련 프로모션 할인 등 외부 서비스 호출용 메소드
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
