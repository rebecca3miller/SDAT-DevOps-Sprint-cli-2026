public abstract class Shape {
    String name;
    


    public Shape(String name) {
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
