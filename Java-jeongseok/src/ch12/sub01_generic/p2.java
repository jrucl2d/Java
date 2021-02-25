package ch12.sub01_generic;

class Fruit2 implements Eatable{
    public String toString() {
        return "Fruit";
    }
}
class Apple2 extends Fruit {
    public String toString() {
        return "Apple";
    }
}
class Grape2 extends Fruit {
    public String toString() {
        return "Grape";
    }
}
class Toy2 {
    public String toString() {
        return "Toy";
    }
}
interface Eatable {}
class FruitBox<T extends Fruit & Eatable> extends Box<T> {}
public class p2 {
    public static void main(String[] args) {

    }
}
