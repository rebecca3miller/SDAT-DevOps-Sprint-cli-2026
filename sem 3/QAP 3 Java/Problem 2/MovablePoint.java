

public class MovablePoint extends Point{
    
    private float xSpeed = 0.0f;
    private float ySpeed = 0.0f;
    
    public MovablePoint(float x, float y, float xSpeed, float ySpeed) {

        super(x, y);

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    };

    //Getters and Setters

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public float[] getSpeed() {
        float[] speed = {xSpeed, ySpeed};
        return speed;
    }


    public void setxSpeed(float newxSpeed) {
        this.xSpeed = newxSpeed;
    }

    public void setySpeed(float newySpeed) {
        this.ySpeed = newySpeed;
    }

    public void setSpeed(float newxSpeed, float newySpeed) {
        this.xSpeed = newxSpeed;
        this.ySpeed = newySpeed;
    }
    

    //to string methood

    public String toString() {
        return

        System.lineSeparator() +
        super.toString() + "," + " speed - " + "(" + xSpeed + "," + ySpeed + ")" +
        System.lineSeparator();
    }
    
    
    
    
    
    
    
}
