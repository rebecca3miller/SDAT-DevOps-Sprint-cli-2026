

public class Ellipse2 extends Shape2 {

    private double a;
    private double b;


    public Ellipse2(String name, double a, double b) {
        super(name);

        if (a >= b) {
            this.a = a;
            this.b = b;
        }

        else {
            this.a = b;
            this.b = a;
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }


    @Override
    public void scale(double factor) {
        a *= factor;
        b *= factor;
    }

    @Override
    public double getArea() {
        return Math.round((Math.PI * a * b) * 100.0) / 100.0; 
    }

    @Override
    public double getPerimeter() {
        return Math.round(Math.PI * Math.sqrt((2 * (a * a + b * b) - ((a - b) * (a - b)) / 2)) * 100.0) / 100.0;
    }
    
}
