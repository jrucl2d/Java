package ch07.before;

public class p7_15 {
    public static void main(String[] args) {
        Car car = null;
        FireEngine fe1 = new FireEngine();
        FireEngine fe2 = null;

        fe1.water();
        car = fe1; // car = (Car) fe1이 생략됨
//      car.water();
        fe2 = (FireEngine) car;
        fe2.water();

        Car car1 = new Car();
        Car car2 = null;
        FireEngine fe = null;

        car1.drive();
        fe = (FireEngine) car1; // 조상 인스턴스(new Car된 car1)을 자식 타입 참조변수(fe)로 참조할 수 없다.
        fe.drive();
        car2 = fe;
        car2.drive();
    }
}
class Car {
    String color;
    int door;
    void drive() {
        System.out.println("Car.drive");
    }
    void stop() {
        System.out.println("Car.stop");
    }
}
class FireEngine extends Car {
    void water() {
        System.out.println("FireEngine.water");
    }
}
