// import java.util.ArrayList;

public class Point {
    
   

    private float x = 0.0f;
    private float y = 0.0f;
    
    
    public Point(float x, float y) {

        this.x = x;
        this.y = y;
    };

    //Getters and Setters

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float[] getXY() {
        float[] xy = {x, y};

        return xy;
        
    }

    public void setX(float newX) {
        this.x = newX;
    }

    public void setY(float newY) {
        this.y = newY;
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }


    //to string method
    public String toString() {
        return

        
        "Point is - " + "(" + getX() + "," + getY() + ")";
        
    }


    // +Point()
    // +getX():float
    // +setX(x:float):void
    // +getY():float
    // +setY(y:float) :void
    // +setXY(x:float,y:float):void
    // +getXY():float[2]
    // +toString():String

    // "(x,y)"
}
