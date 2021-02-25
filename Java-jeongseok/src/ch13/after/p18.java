package ch13.after;

public class p18 {
    public static void main(String[] args) {
        Thread5 t1 = new Thread5("*");
        Thread5 t2 = new Thread5("**");
        Thread5 t3 = new Thread5("***");
        t1.start();
        t2.start();
        t3.start();

        try{
            Thread.sleep(2000);
            t1.suspend();
            Thread.sleep(2000);
            t2.suspend();
            Thread.sleep(3000);
            t1.resume();
            Thread.sleep(3000);
            t1.stop();
            t2.stop();
            Thread.sleep(3000);
            t3.stop();

        } catch (InterruptedException e){}
    }
}
class Thread5 implements Runnable {
    boolean suspended = false;
    boolean stopped = false;

    Thread th;
    Thread5(String name){
        th = new Thread(this, name);
    }
    @Override
    public void run() {
        String name = th.getName();
        while(!stopped) {
            if(!suspended){
                System.out.println("name = " + name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("name " + name + " interrupted");
                }
            } else {
                // suspend인 상태에서 의미없이 while문을 도는 busy-waiting
                // 대신에 yield를 호출해서 효율적으로 다른 쓰레드에게 양보
                Thread.yield();
            }
        }
        System.out.println(th.getName() + " stopped");
    }
    public void suspend() {
        suspended = true;
        th.interrupt(); // 만약 쓰레드가 sleep에 머물러 있더라면 interrupt를 이용해 바로 깨운다.
        System.out.println("INTERRUPTED!! Thread5.suspend");
    }
    public void stop() {
        stopped = true;
        th.interrupt();
        System.out.println("INTERRUPTED!! Thread5.stop");
    }
    public void resume() {suspended = false;}
    public void start() {th.start();}
}
