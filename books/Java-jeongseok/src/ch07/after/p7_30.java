package ch07.after;

public class p7_30 {
    public static void main(String[] args) {
        Child c = new Child();
        c.method1();
        c.method2();
        MyInterface.staticMethod();
        MyInterface2.staticMethod();
    }
}
class Child extends Parent implements MyInterface, MyInterface2 {
    public void method1() {
        System.out.println("Child.method1");
    }
}
class Parent {
    public void method2() {
        System.out.println("Parent.method2");
    }
}
interface MyInterface {
    default void method1() {
        System.out.println("MyInterface.method1");
    }
    default void method2(){
        System.out.println("MyInterface.method2");
    }
    static void staticMethod() {
        System.out.println("MyInterface.staticMethod");
    }
}
interface MyInterface2 {
    default void method1(){
        System.out.println("MyInterface2.method1");
    }
    static void staticMethod() {
        System.out.println("MyInterface2.staticMethod");
    }
}