package ch07.after;

public class p7_26 {
    public static void main(String[] args) {
        Tank tank = new Tank();
        Dropship dropship = new Dropship();
        Marine marine = new Marine();
        SCV scv = new SCV();
        scv.repair(tank);
        scv.repair(dropship);
//        scv.repair(marin); // 치료 불가
    }
}
interface Repairable {}

class Unit {
    int hitPoint;
    final int MAX_HP;
    Unit(int hp){
        MAX_HP = hp;
    }
}
class GroundUnit extends Unit {
    GroundUnit(int hp){
        super(hp);
    }
}
class AirUnit extends Unit {
    AirUnit(int hp){
        super(hp);
    }
}

class Tank extends GroundUnit implements Repairable {
    Tank() {
        super(150);
        hitPoint = MAX_HP;
    }

    @Override
    public String toString() {
        return "tank";
    }
}
class Dropship extends AirUnit implements Repairable {
    Dropship() {
        super(125);
        hitPoint = MAX_HP;
    }

    @Override
    public String toString() {
        return "dropship";
    }
}
class Marine extends GroundUnit { // 치료 불가
    Marine() {
        super(40);
        hitPoint = MAX_HP;
    }
}
class SCV extends GroundUnit implements Repairable {
    SCV() {
        super(60);
        hitPoint = MAX_HP;
    }
    void repair(Repairable r){
        if(r instanceof Unit) {
            Unit u = (Unit) r;
            while(u.hitPoint != u.MAX_HP){
                u.hitPoint += 1;
            }
            System.out.println(u.toString() + "의 수리가 끝났습니다.");
        }
    }
}