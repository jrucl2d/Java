package ch12.sub01_generic;

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
class Toy {
    public String toString() {
        return "Toy";
    }
}
class Box<T> {
    List<T> list = new ArrayList<>();
    void add(T item) {list.add(item);}
    T get(int i) {return list.get(i);}
    int size() {return list.size();}
    public String toString() {
        return list.toString();
    }
}
public class p1 {
    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<>();
        Box<Apple> appleBox = new Box<>();
        Box<Toy> toyBox = new Box<>();
        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());

        appleBox.add(new Apple());
        toyBox.add(new Toy());

        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("toyBox = " + toyBox);
    }
}
