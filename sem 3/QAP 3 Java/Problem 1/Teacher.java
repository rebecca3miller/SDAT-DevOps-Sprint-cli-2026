

public class Teacher extends Person {
    
    protected String subject;
    protected double salary;
    


    public Teacher(String name, int age, String gender, String subject, double salary) {

        // use the super class’s constructor 
        super(name, age, gender);

        // initialize what’s new to Teacher
        this.subject = subject;
        this.salary = salary;
    }

    // To String methood

    public String toString() {
        return 
        
        System.lineSeparator() +
        super.toString() + ", Subject - " + subject + ", Salary - $" + salary +
        System.lineSeparator();
    }

    // Getters and Setters

    public String getSubject() {
        return subject;
    }

    public double getSalary() {
        return salary;
    }


    public void setSubject(String newSubject) {
        this.subject = newSubject;
    }

    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }


}
