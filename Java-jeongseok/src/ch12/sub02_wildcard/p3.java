package ch12.sub02_wildcard;

import java.util.ArrayList;
import java.util.List;

class Fruit {
    public String toString() {
        return "Fruit";
    }
}
class Apple extends Fruit {
    public String toString() {
        return "Apple";
    }
}
class Grape extends Fruit {
    public String toString() {
        return "Grape";
    }
}
class Juice {
    String name;
    Juice(String name){
        this.name = name + "Juice";
    }
    public String toString() {
        return name;
    }
}
class FruitBox<T extends Fruit> extends Box<T> {}
class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";
        for (Fruit f : box.getList()) {
            tmp += f + " ";
        }
        return new Juice(tmp);
    }
}
class Box<T> {
    List<T> list = new ArrayList<>();
    void add(T item) {list.add(item);}
    T get(int i) {return list.get(i);}
    int size() {return list.size();}
    List<T> getList() {return list;}
    public String toString() {
        return list.toString();
    }
}
public class p3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleFruitBox = new FruitBox<>();

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleFruitBox.add(new Apple());
        appleFruitBox.add(new Apple());

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleFruitBox));
    }
}
