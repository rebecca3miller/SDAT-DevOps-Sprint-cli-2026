

public class EquilateralTriangle extends Shape {
    
    private double side1;
    private double side2;
    private double side3;


    public EquilateralTriangle(String name, double side1, double side2, double side3) {
        super(name);

        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new IllegalArgumentException("Sides must be greater than 0");
        }

        if (side1 == side2 && side2 == side3) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }

        else {
            throw new IllegalArgumentException("All sides must be equal for an equilateral triangle");
        }
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    @Override
    public double getArea() {
        return Math.round((Math.sqrt(3) / 4) * side1 * side1 * 100.0) / 100.0;
    }

    @Override
    public double getPerimeter() {
        return Math.round((side1 + side2 + side3) * 100.0) / 100.0;
    }
}


