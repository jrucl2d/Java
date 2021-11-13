package ch13.after.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class p23 {
    public static void main(String[] args) {
        Table table = new Table(); // 공유하는 객체

        new Thread(new Cook(table), "요리사").start();
        new Thread(new Customer(table, "donut"), "고객1").start();
        new Thread(new Customer(table, "burger"), "고객2").start();
        try { Thread.sleep(2000); } catch (InterruptedException e) { }
        System.exit(0);
    }
}

class Customer implements Runnable {
    private Table table;
    private String food;
    Customer(Table table, String food){
        this.table = table;
        this.food = food;
    }
    public void run() {
        while(true){
            try{ Thread.sleep(100); } catch (InterruptedException e){}
            String name = Thread.currentThread().getName();
            table.remove(food);
            System.out.println(name + "이 먹은 음식 : " + food);
        }
    }
}
class Cook implements Runnable {
    private Table table;
    Cook(Table table) { this.table = table; }
    public void run() {
        while(true) {
            // 랜덤한 음식 추가
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try{ Thread.sleep(50); } catch (InterruptedException e){}
        }
    }
}
class Table {
    String[] dishNames = {"donut", "donut", "burger"};
    final int MAX_FOOD = 6; // 테이블 위 음식 최대 숫자
    private List<String> dishes = new ArrayList<>();

    // 동기화가 필요한 부분
    public synchronized void add(String dish){
        while(dishes.size() >= MAX_FOOD){
            String name = Thread.currentThread().getName();
            System.out.println(name + "가 테이블이 꽉 차서 기다리는 중...");
            try{
                wait(); // 요리사 쓰레드가 기다림
                Thread.sleep(500);
            } catch (InterruptedException ex) {}
        }
        dishes.add(dish);
        notify(); // 손님 쓰레드를 깨움
        System.out.println("현재 음식 : " + dishes.toString());
    }
    public void remove(String dishName) {
        // 동기화가 필요한 부분
        synchronized (this) {
            String name = Thread.currentThread().getName();
            while(dishes.size() == 0){
                System.out.println(name +"은 테이블에 음식이 없어 기다리는 중...");
                try{
                    wait(); // 손님 쓰레드가 기다림
                    Thread.sleep(500);
                } catch (InterruptedException ex) {}
            }
            while(true) {
                for(int i = 0; i < dishes.size(); i++){
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify(); // 요리사 쓰레드 깨움
                        return;
                    }
                }
                try{
                    System.out.println(name +"은 원하는 음식이 없어 기다리는 중...");
                    wait();
                    Thread.sleep(500);
                } catch (InterruptedException ex) {}
            }

        }
    }
    public int dishNum() { return dishNames.length; }
}