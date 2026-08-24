

public class Person {

    protected String myName ; // name of the person 
    protected int myAge; // person’s age 
    protected String myGender; // “M” for male, “F” for female
    


    public Person(String name, int age, String gender) {

    this.myName = name; 
    this.myAge = age ; 
    this.myGender = gender; 

    }

    

    //To string method
    @Override
    public String toString() { 
        return
        
        System.lineSeparator() +
        "Name - " + myName + ", Age - " + myAge + ", Gender - " + myGender +
        System.lineSeparator(); 
    }

    //Getters and Setters
    public String getName() {
        return myName;
    }

    public int getAge() {
        return myAge;
    }

    public String getGender() {
        return myGender;
    }
    


    public void setName(String newName) {
        this.myName = newName;
    }

    public void setAge(int newAge) {
        this.myAge = newAge;
    }

    public void setGender(String newGender) {
        this.myGender = newGender;
    }

}
