

public class Triangle extends Shape {
    
    private double a;
    private double b;
    private double c;

    public Triangle(String name, double a, double b, double c) {
        super(name);

        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Sides must be greater than 0");
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Invalid triangle sides");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getPerimeter() {
        return Math.round((a + b + c) * 100.0) / 100.0;
    }

    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.round(Math.sqrt(s * (s - a) * (s - b) * (s - c)) * 100.0) / 100.0;
    }
}
