

public class Demo3 {
    public static void main(String[] args) {
        
        Circle C1 = new Circle("Circle", 2.3);

        Ellipse S1 = new Ellipse("Ellipse", 2.3, 3);

        Triangle T1 = new Triangle("Triangle", 3, 4, 5);

        EquilateralTriangle ET1 = new EquilateralTriangle("Equilateral Triangle", 3, 3,3);



        System.out.println(C1.toString());
        System.out.println(S1.toString());
        System.out.println(T1.toString());
        System.out.println(ET1.toString());
        
    }
}
