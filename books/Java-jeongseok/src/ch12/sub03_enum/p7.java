package ch12.sub03_enum;

enum Transportation {
    BUS(100) { int fare(int dist) { return dist * BASIC_FARE;}},
    TRAIN(150) { int fare(int dist) { return dist * BASIC_FARE;}},
    SHIP(100) { int fare(int dist) { return dist * BASIC_FARE * 3;}},
    AIRPLANE(300) { int fare(int dist) { return dist * BASIC_FARE * 2;}};


    protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근이 가능

    Transportation(int basicFare) {
        BASIC_FARE = basicFare;
    }
    public int getBasicFare() {return BASIC_FARE;}
    abstract int fare(int distant);
}
public class p7 {
    public static void main(String[] args) {
        System.out.println("Transportation.BUS.fare(2) = " + Transportation.BUS.fare(2));
        System.out.println("Transportation.TRAIN.fare(3) = " + Transportation.TRAIN.fare(3));
        System.out.println("Transportation.sH = " + Transportation.SHIP.fare(4));
        System.out.println("Transportation.AIRPLANE.fare(5) = " + Transportation.AIRPLANE.fare(5));
    }
}
