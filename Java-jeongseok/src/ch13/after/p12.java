package ch13.after;

public class p12 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());
        t1.start();
        t2.start();
        try{
            t1.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("p12.main end");
    }
}
class Thread1 implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 300; i++){
            System.out.print("-");
        }
        System.out.println("Thread1.run end");
    }
}
class Thread2 implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 300; i++){
            System.out.print("|");
        }
        System.out.println("Thread2.run end");
    }
}