package ch13.before;

class Thread4 extends Thread3{
    public void run() {
        throwException();
    }
    private void throwException() {
        try{
            throw new Exception();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class p3 {
    public static void main(String[] args) {
        Thread4 t1 = new Thread4();
        t1.run();
    }
}
