

public class Circle extends Shape{
    
    private double a;
    

    public Circle(String name, double a) {
        super(name);
        this.a = a;
    }

    public double getA() {
        return a;
    }

    @Override
    public double getPerimeter() {
        return Math.round(2 * Math.PI * getA() * 100.0) / 100.0;
    }

    @Override
    public double getArea() {
        return Math.round(Math.PI * Math.pow(getA(), 2) * 100.0) / 100.0;
    }
}
