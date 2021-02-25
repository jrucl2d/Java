package ch13.before;

public class p1 {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();

//        Runnable r = new Thread2();
//        Thread t2 = new Thread(r);
        Thread t2 = new Thread(new Thread2());
        t2.start();
    }
}
class Thread1 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println(getName());
        }
    }
}
class Thread2 implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}