/*
 * Q1. Design an abstract class Shape with abstract area() and a concrete describe().
 *     Create Circle, Rectangle, Triangle. Using runtime polymorphism, find the shape
 *     with the largest area from a mixed array.
 */
abstract class Shape {
    abstract double area();

    String describe() {
        return getClass().getSimpleName() + " with area " + String.format("%.2f", area());
    }
}

class Circle extends Shape {
    private final double r;
    Circle(double r) { this.r = r; }
    double area() { return Math.PI * r * r; }
}

class Rectangle extends Shape {
    private final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }
    double area() { return w * h; }
}

class Triangle extends Shape {
    private final double b, h;
    Triangle(double b, double h) { this.b = b; this.h = h; }
    double area() { return 0.5 * b * h; }
}

public class Q01_AbstractShapes {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(3), new Rectangle(4, 6), new Triangle(10, 5), new Circle(1.5) };
        Shape largest = shapes[0];
        for (Shape s : shapes) {
            System.out.println(s.describe());   // dynamic dispatch
            if (s.area() > largest.area()) largest = s;
        }
        System.out.println("Largest: " + largest.describe());
    }
}
