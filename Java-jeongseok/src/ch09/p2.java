package ch09;

import java.util.Objects;

public class p2 {
    public static void main(String[] args) {
        Person p1 = new Person(12341509325093L);
        Person p2 = new Person(12341509325093L);

        if(p1 == p2){
            System.out.println("same person");
        } else {
            System.out.println("not same person");
        }
        if(p1.equals(p2)){
            System.out.println("same person");
        } else {
            System.out.println("not same person");
        }
    }
}
class Person {
    long id;

    @Override
    public boolean equals(Object o) {
        if(o != null && o instanceof Person){
            return id == ((Person) o).id;
        }
        else {
            return false;
        }
    }
    Person(long id){
        this.id = id;
    }
}
