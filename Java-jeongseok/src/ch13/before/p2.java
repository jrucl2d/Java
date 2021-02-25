package ch13.before;

class Thread3 extends Thread {
    public void run() {
        throwException();
    }
    private void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class p2 {
    public static void main(String[] args) {
        Thread3 t1 = new Thread3();
        t1.start();
    }
}
