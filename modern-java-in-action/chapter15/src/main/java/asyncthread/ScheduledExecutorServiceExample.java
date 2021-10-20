package asyncthread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService
                = Executors.newScheduledThreadPool(1);

        work1();
        scheduledExecutorService.schedule(
                ScheduledExecutorServiceExample::work2, 10, TimeUnit.SECONDS
        ); // work1 끝난 뒤 10초 뒤에 work2를 개별 태스크로 스캐줄함

        scheduledExecutorService.shutdown();

    }

    public static void work1() {
        System.out.println("Hello from Work1");
    }

    public static void work2() {
        System.out.println("Hello from Work2");
    }
}
