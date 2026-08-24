public abstract class Shape2 implements Scalable {
    String name;
    


    public Shape2(String name) {
        this.name = name;
        

    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public abstract double getPerimeter();

    public abstract double getArea();


    @Override
    public String toString() {
        return "Name - " + name +
                ", Area - " + getArea() +
                ", Perimeter - " + getPerimeter() +
                System.lineSeparator();
    }


    


}
