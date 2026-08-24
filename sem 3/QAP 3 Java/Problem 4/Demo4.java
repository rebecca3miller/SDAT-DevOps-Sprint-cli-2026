public class Demo4 {

    public static void scaleAll(Scalable[] shapes, double factor) {

        System.out.println("Before scaling:");

        for (Scalable shape : shapes) {
            System.out.println(shape);
        }

        for (Scalable shape : shapes) {
            shape.scale(factor);
        }

        System.out.println("\nAfter scaling:");

        for (Scalable shape : shapes) {
            System.out.println(shape);
        }
    }

    public static void main(String[] args) {

        Circle2 circle2 = new Circle2("Circle", 5);

        Ellipse2 ellipse2 = new Ellipse2("Ellipse", 6, 4);

        Triangle2 triangle2 = new Triangle2("Triangle", 2, 4, 5);

        EquilateralTriangle2 equilateralTriangle2 = new EquilateralTriangle2("Equilateral Triangle", 5, 5, 5);

        Scalable[] shapes = {
                circle2,
                ellipse2,
                triangle2,
                equilateralTriangle2
        };

        scaleAll(shapes, 2);
    }
}