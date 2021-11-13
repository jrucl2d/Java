package ch09;

public class p7 {
    public static void main(String[] args) {
        Point original = new Point(3, 5);
        Point copy = original.clone();
        System.out.println("original = " + original);
        System.out.println("copy = " + copy);
    }
}
class Point implements Cloneable {
    int x, y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "Point{" + "x=" + x +", y=" + y +'}';
    }
    // protected 접근 제어자를 public으로 해줘야 한다.
    public Point clone() {
        Object o = null;
        try {
            o = super.clone(); // clone()은 반드시 예외처리를 해줘야 한다.
        } catch (CloneNotSupportedException e){}
        return (Point) o;
    }
}