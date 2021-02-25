package ch13.after;

public class p21 {
    public static void main(String[] args) {
        Runnable r = new Thread10();
        new Thread(r).start();
        new Thread(r).start();
    }
}
class Account {
    // balance가 private이 아니면 외부에서 접근 가능하므로 동기화가 깨질 수 있다.
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }
//    public synchronized void withdraw(int money)
    public void withdraw(int money) {
        synchronized (this){ // 락을 걸고자 하는 객체를 참조하는 참조 변수
            if(balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                balance -= money;
            }
        }
    }
}
class Thread10 implements Runnable {
    Account acc = new Account();
    @Override
    public void run() {
        while(acc.getBalance() > 0){
            int money = (int)(Math.random() * 3 + 1) * 100; // 100, 200, 300
            acc.withdraw(money);
            System.out.println("acc.getBalance() = " + acc.getBalance());
        }
    }
}