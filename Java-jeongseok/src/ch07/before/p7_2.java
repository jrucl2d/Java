package ch07.before;

public class p7_2 {
    public static void main(String[] args) {
        Point[] p = {
                new Point(100, 100),
                new Point(140, 50),
                new Point(200, 100)
        };
        Triangle t = new Triangle(p);
        Circle c = new Circle(new Point(150, 150), 50);
        t.draw();
        c.draw();
    }

}

class Shape {
    String color = "black";

    void draw() {
        System.out.printf("[color=%s]%n", color);
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point() {
        this(0, 0);
    }

    String getXY() {
        return "(" + x + ", " + y + ")";
    }
}

class Circle extends Shape {
    Point center;
    int r;

    Circle(Point center, int r) {
        this.center = center;
        this.r = r;
    }

    Circle() {
        this(new Point(0, 0), 100);
    }

    void draw() {
        System.out.println("[center=(" + center.x + ", " + center.y + "), r = " + r + ", color = " + color);
    }
}

class Triangle extends Shape {
    Point[] points = new Point[3];

    Triangle(Point[] points) {
        this.points = points;
    }

    void draw() {
        System.out.println("p1 = " + points[0].getXY() + "p2 = " + points[1].getXY() + "p3 = " + points[2].getXY() + "color = " + color);
    }
}