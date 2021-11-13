package ch13.before;

public class p10 implements Runnable{
    private static boolean autoSave = false;
    public static void main(String[] args) {
        Thread t = new Thread(new p10());
        t.setDaemon(true);
        t.start();
        for(int i =1; i <10; i++){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            System.out.println("i = " + i);
            if(i == 5){
                autoSave = true;
            }
        }
    }
    public void run() {
        while(true) {
            try{
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {}
            if(autoSave){
                autosave();
            }
        }
    }
    private void autosave() {
        System.out.println("p10.autosave");
    }
}
