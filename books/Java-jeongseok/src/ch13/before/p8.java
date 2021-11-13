package ch13.before;

public class p8 {
    public static void main(String[] args) {
        Thread6 t1 = new Thread6();
        Thread7 t2 = new Thread7();

        t2.setPriority(7);
        System.out.println("t2.getPriority() = " + t2.getPriority());
        System.out.println("t1.getPriority() = " + t1.getPriority());
        t1.start();
        t2.start();
    }
}
class Thread6 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i <300; i++){
            System.out.print("-");
            for(int j = 0; j < 1000000; j++);
        }
    }
}
class Thread7 extends Thread {
    @Override
    public void run() {
        for(int i =0; i <300; i++){
            System.out.print("|");
            for(int j = 0; j < 1000000; j++);
        }
    }
}