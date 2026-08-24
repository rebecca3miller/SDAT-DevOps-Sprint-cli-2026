

public class Student extends Person { 
    
    protected int myIdNum; // Student Id Number 
    protected double myGPA; // grade point average
    protected static int idCounter = 1;
  

    public Student(String name, int age, String gender, int idNum, double gpa) { 
        // use the super class’ constructor 
        super(name, age, gender);
        // initialize what’s new to Student
        this.myIdNum = idCounter++;
        this.myGPA = gpa;

    } 


    //ToString method
    @Override
    public String toString() {
        return
        
        System.lineSeparator() +
        super.toString() + ", Id Number - " + myIdNum + ", GPA - " + myGPA +
        System.lineSeparator();
    }


    //Getters and Setters
    public int getIdNum() {
        return myIdNum;
    }

    public double getGPA() {
        return myGPA;
    }


    public void setIdNum(int newIdNum) {
        this.myIdNum = newIdNum;
    }

    public void setGPA(double newGPA) {
        this.myGPA = newGPA;
    }


}