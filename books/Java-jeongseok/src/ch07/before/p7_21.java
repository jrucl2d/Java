package ch07.before;

import java.util.ArrayList;
import java.util.List;

public class p7_21 {
    public static void main(String[] args) {
        Audio audio = new Audio();
        Buyer b = new Buyer();
        b.buy(new TV2());
        b.buy(new Computer());
        b.buy(audio);
        b.summary();
        b.refund(audio);
        b.summary();
    }
}
class Product {
    int price;
    int bonusPoint;
    Product(int price) {
        this.price = price;
        bonusPoint = (int) (price / 10.0);
    }
}
class TV2 extends Product {
    TV2 () {
        super(100);
    }
    @Override
    public String toString() {
        return "TV{" +
                "price=" + price +
                ", bonusPoint=" + bonusPoint +
                '}';
    }
}
class Computer extends Product {
    Computer(){
        super(200);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "price=" + price +
                ", bonusPoint=" + bonusPoint +
                '}';
    }
}
class Audio extends Product {
    Audio() {
        super(50);
    }

    @Override
    public String toString() {
        return "Audio{" +
                "price=" + price +
                ", bonusPoint=" + bonusPoint +
                '}';
    }
}
class Buyer {
    int money = 1000;
    int bonusPoint = 0;
    List<Product> item = new ArrayList<>();

    void buy(Product p){
        if(money < p.price){
            System.out.println("잔액 부족");
            return;
        }
        money -= p.price;
        bonusPoint += p.bonusPoint;
        item.add(p);
        System.out.println(p + "구매 성공");
    }
    void summary() {
        int sum = 0;
        String itemList = "";

        for(Product tem : item){
            if(tem == null) break;
            sum += tem.price;
            itemList += tem + ", ";
        }
        System.out.println("sum = " + sum);
        System.out.println("itemList = " + itemList);
    }
    void refund(Product p) {
        if(item.remove(p)) {
            money += p.price;
            bonusPoint -= p.bonusPoint;
            System.out.println(p + "를 반품");
        } else {
            System.out.println("그런 물품 산 적 없음");
        }
    }
}