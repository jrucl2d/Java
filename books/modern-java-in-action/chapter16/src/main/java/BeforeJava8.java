import java.util.concurrent.*;

public class BeforeJava8 {
    public static void main(String[] args) {
        // 스레드 풀에 태스크 제출하려면 ExecutorService 필요
        ExecutorService executor = Executors.newCachedThreadPool();
        // Callable 을 ExecutorService 로 제출
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
//                return doSomeLongComputation();
                return null; // 시간이 오래 걸리는 작업을 다른 스레드에서 비동기로 실행
            }
        });
//        doSomethingElse(); // 비동기 작업 수행 동안 다른 작업
        try {
            // 비동기 작업의 결과 가져옴.
            // 결과가 준비되어 있지 않으면 호출 스레드(현제 스레드)가 블록되지만 최대 1초만 기다린다.
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            // 계산 중 예외 발생
            e.printStackTrace();
        } catch (InterruptedException e) {
            // 현재 스레드에서 대기 중 인터럽트 발생
            e.printStackTrace();
        } catch (TimeoutException e) {
            // Future 완료되기 전에 타임아웃 발생
            e.printStackTrace();
        }
    }
}
