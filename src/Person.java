//Broader point of view  - Person
//Player 1 class extends person class
//player 1 represents the user that was inherited from Person class
public abstract class Person {
    private  String firstName; //private  variable
    private String stance;  //private variable


//getters and setters for variables - firstName  and moves
    public Person() {}

    //setter
    public Person(String firsName) {this.firstName = firstName;}
    public void setFirstName(String newfirstName) {
        firstName = newfirstName;
}
//getter
    public String getFirstName() {
        return firstName;
    }

    //getter for stance variable
     public String getStance() {
        return stance;
    }
    //setter for stance variable
    public void setStance(String newStance) {
        stance =  newStance;
    }
    //method - this can be done since person class is public abstract class
    public abstract void selectStance();
}






