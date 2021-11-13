package ch13.before;

public class p9 {
    public static void main(String[] args) {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup group1 = new ThreadGroup("Group1");
        ThreadGroup group2 = new ThreadGroup("Gropu2");

        ThreadGroup subGroup1 = new ThreadGroup(group1, "SubGroup1");

        group1.setMaxPriority(3);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e) {}
            }
        };

        new Thread(group1, r, "th1").start();
        new Thread(subGroup1, r, "th2").start();
        new Thread(group2, r, "th3").start();
        System.out.println("main.getName() = " + main.getName());
        System.out.println("main.activeGroupCount() = " + main.activeGroupCount());
        System.out.println("main.activeCount() = " + main.activeCount());
        main.list();
    }
}
