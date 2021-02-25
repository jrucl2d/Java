package ch13.after;

public class p20 {
    public static void main(String[] args) {
        Thread8 gc = new Thread8();
        gc.setDaemon(true);
        gc.start();

        int requiredMemory = 0;
        for(int i = 0; i < 20; i++){
            requiredMemory = (int) (Math.random() * 10) * 20;

            if(gc.freeMemory() < requiredMemory || gc.freeMemory() < gc.totalMemory() * 0.4){
                gc.interrupt();
                try {
                    gc.join(100); // 이 부분이 없다면 gc 실행 전에 main 쓰레드가 실행되어 버려 1000이 넘는 값이 나올 수 있다.
                } catch (InterruptedException e) { }
            }
            gc.usedMemory += requiredMemory;
            System.out.println("gc.usedMemory = " + gc.usedMemory);
        }
    }
}
class Thread8 extends Thread{
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt");
            }
            gc();
            System.out.println("garbage collect : " + freeMemory());
        }
    }
    public void gc() {
        usedMemory -= 300;
        if(usedMemory < 0) usedMemory = 0;
    }
    public int totalMemory() {return MAX_MEMORY;}
    public int freeMemory() {return MAX_MEMORY - usedMemory;}
}