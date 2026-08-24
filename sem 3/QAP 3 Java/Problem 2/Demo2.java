

public class Demo2 {
    
    public static void main(String[] args) {
        
        Point P1 = new Point(9.3f, 7.8f);
        MovablePoint M1 = new MovablePoint(23.6f, 21.4f, 5.2f, 8.3f);
        float[] xy = P1.getXY();
        // float[] speed = M1.getSpeed();


        System.out.println(P1.toString());
        System.out.println(M1.toString());
       
        System.out.println();
        System.out.println(P1.getX());
        System.out.println(P1.getY());

        
        System.out.println("(" + xy[0] + "," + xy[1] + ")");
        
        
        
        P1.setX(5.0f);
        P1.setY(5.0f);

        System.out.println(P1.getX());
        System.out.println(P1.getY());
        xy = P1.getXY();
        System.out.println("(" + xy[0] + "," + xy[1] + ")");
        

        P1.setXY(4.0f, 3.0f);
        xy = P1.getXY();
        System.out.println("(" + xy[0] + "," + xy[1] + ")");

        

        System.out.println(P1.toString());



    }
}
