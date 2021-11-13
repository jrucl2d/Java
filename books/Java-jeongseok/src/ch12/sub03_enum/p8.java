package ch12.sub03_enum;

abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
    static int id = 0;
    private int ordinal;
    protected String name = "";

    public int ordinal() {return ordinal;}
    MyEnum(String name){
        this.name = name;
        ordinal = id++;
    }
    public int compareTo(T t){
        return this.ordinal - t.ordinal();
    }
}

abstract class MyTransportation extends MyEnum {
    static final MyTransportation BUS = new MyTransportation("BUS", 100) {
        int fare(int dist) {
            return dist * BASIC_FARE;
        }
    };
    static final MyTransportation SHIP = new MyTransportation("BUS", 100) {
        int fare(int dist) {
            return dist * BASIC_FARE * 3;
        }
    };
    protected final int BASIC_FARE;
    private MyTransportation(String name, int basicF){
        super(name);
        BASIC_FARE = basicF;
    }
    abstract int fare(int dist);
    String name() {return name;}
    public String toString() {return name;}
}
public class p8 {
    public static void main(String[] args) {
        MyTransportation m1 = MyTransportation.BUS;

        MyTransportation m2 = MyTransportation.SHIP;

        System.out.println("m1.name(), = " + m1.name() + m1.ordinal());
        System.out.println("m2.name(),  = " + m2.name() + m2.ordinal());
        System.out.println((m1 == m2));
        System.out.println("m1.compareTo(m2) = " + m1.compareTo(m2));
    }
}
