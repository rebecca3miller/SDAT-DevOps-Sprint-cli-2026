

public class CollegeStudent extends Student {
    
    protected String major;
    protected int year;
    

    public CollegeStudent(String name, int age, String gender, int idnum, double gpa, String major, int year) {

        super(name, age, gender, idnum, gpa);

        this.major = major;
        this.year = year;
    }

    // To String methood

    public String toString() {
        return
        
        System.lineSeparator() +
        super.toString() + ", Major - " + major + ", Attending Year - " + year +
        System.lineSeparator();
    }

    // Getters and Setters

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }


    public void setMajor(String newMajor) {
        this.major = newMajor;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }
    

}
