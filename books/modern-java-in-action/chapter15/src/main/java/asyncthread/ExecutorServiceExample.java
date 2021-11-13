package asyncthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1;

        // Runnable 사용보다 단순하지만 submit 같은 불필요한 메소드 호출 존재
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> f(x));
        Future<Integer> z = executorService.submit(() -> g(x));
        System.out.println(y.get() + z.get());

        executorService.shutdown(); // 반드시 종료해줘야 함
    }

    private static int g(int x) {
        return x * 10;
    }

    private static int f(int x) {
        return x + 10;
    }
}
