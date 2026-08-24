
public class Demo {
    public static void main(String[] args) {

        // Creating the class objects and populating with base data
        Person P1 = new Person("Morgan", 20, "M");
        Student S1 = new Student("John", 34, "M", 0, 3.1);
        Teacher T1 = new Teacher("Bob Mason", 46, "M", "Chemistry", 90000);
        CollegeStudent C1 = new CollegeStudent("Sarah Mitchell", 23, "F", 0, 3.5, "Math", 3);

        //Print out class objects
        System.out.println(P1);
        System.out.println(S1);
        System.out.println(T1);
        System.out.println(C1);

        //Testing set and get methods by changing college student data and reprinting it to the screen
        C1.setMajor("Science");
        C1.setYear(60);
        
        System.out.println(C1.getMajor());
        System.out.println(C1.getYear());
        System.out.println(C1);

    }
}
