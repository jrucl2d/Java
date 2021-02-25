package ch13.after;

public class p19 {
    static long startTime = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread6());
        Thread t2 = new Thread(new Thread7());
        t1.start();
        t2.start();
        startTime = System.currentTimeMillis();
        try{
            t1.join(); // main 쓰레드가 t1의 종료까지 기다림
            t2.join(); // main 쓰레드가 t2의 종료까지 기다림
        } catch (InterruptedException e) {}
        System.out.println("startTime = " + (System.currentTimeMillis() - p19.startTime));
    }
}
class Thread6 implements Runnable{
    public void run() {
        for(int i = 0; i < 300; i++){
            System.out.print(new String("-"));
        }
    }
}
class Thread7 implements Runnable{
    public void run() {
        for(int i = 0; i < 300; i++){
            System.out.print(new String("|"));
        }
    }
}