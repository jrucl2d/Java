package ch13.after;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class p27 {
    static final ForkJoinPool pool = new ForkJoinPool(); // 쓰레드 풀을 생성
    public static void main(String[] args) {
        long from = 1L, to = 100_000_000L;

        SumTask task = new SumTask(from, to);

        long start = System.currentTimeMillis();
        Long result = pool.invoke(task); // 작업 시작
        System.out.println("걸린 시간(멀티쓰레드) : " + (System.currentTimeMillis() - start));
        System.out.println("result = " + result);

        result = 0L;
        start = System.currentTimeMillis();
        for(long i = from; i <= to; i++){
            result += i;
        }
        System.out.println("걸린 시간(싱글 쓰레드) : " + (System.currentTimeMillis() - start));
        System.out.println("result = " + result);
    }
}
class SumTask extends RecursiveTask<Long> {
    long from, to;
    SumTask(long from, long to){
        this.from = from;
        this.to = to;
    }
    @Override
    protected Long compute() {
        long size = to - from + 1; // from <= i <= to

        if (size <= 5){
            return sum(); // 5개 이하면 sum 실행
        }
        long half = (from + to) / 2;
        SumTask leftSum = new SumTask(from, half);
        SumTask rightSum = new SumTask(half + 1, to);
        leftSum.fork(); // 작업 큐에 넣음
        return rightSum.compute() + leftSum.join();
    }
    long sum() {
        long tmp = 0L;
        for(long i = from; i<= to; i++){
            tmp += i;
        }
        return tmp;
    }
}