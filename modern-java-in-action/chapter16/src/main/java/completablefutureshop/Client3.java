package completablefutureshop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Client3 {
    private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("FifthShop"));

    public static void main(String[] args) {
        long start = System.nanoTime();
        findPricesAsync("myPhone27S");
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static List<String> findPricesAsync(String product) {
        final Executor executor =
                Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                        new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                Thread t = new Thread(r);
                                t.setDaemon(true); // 프로그램 종료를 방해하지 않는 데몬 스레드 사용
                                return t;
                            }
                        });
        // thenApply 는 CompletableFuture 가 끝날 때까지 블록하지 않음.
        // 즉, CompletableFuture 가 완전히 동작을 완료한 다음에 thenApply 메소드로 전달된 람다 표현식 적용 가능.
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync( // 비동기로 상점에서 정보 조회
                                () -> shop.getPrice(product), executor
                        ))
                        .map(future -> future.thenApply(Quote::parse)) // parsing 에는 지연 과정이 없으므로 즉시 수행
                        .map(future -> future.thenCompose(quote -> // 두 비동기 연산을 파이프라인으로 만드는 thenCompose
                                CompletableFuture.supplyAsync(
                                        () -> Discount.applyDiscount(quote), executor
                                )))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // 가격 정보 구하는 데 5초, 할인 서비스 적용에 5초 총 10초 걸림
    public static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }
}
