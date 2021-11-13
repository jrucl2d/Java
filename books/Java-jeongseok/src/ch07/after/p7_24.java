package ch07.after;

public class p7_24 {
    public static void main(String[] args) {
        Fighter fighter = new Fighter();
        if (fighter instanceof Unit2) {
            System.out.println("Unit");
        }
        if (fighter instanceof Fightable) {
            System.out.println("Fightable");
        }
        if (fighter instanceof Movable) {
            System.out.println("Movable");
        }
        if (fighter instanceof Attackable) {
            System.out.println("Attackable");
        }
        if (fighter instanceof Object) {
            System.out.println("Object");
        }
    }
}

class Fighter extends Unit2 implements Fightable {
    @Override
    public void move(int x, int y) {
    }

    @Override
    public void attack(Unit u) {
    }
}

class Unit2 {
    int currentHP;
    int x;
    int y;
}

interface Fightable extends Movable, Attackable {
}

interface Movable {
    void move(int x, int y);
}

interface Attackable {
    void attack(Unit u);
}