import java.util.Scanner;
//incorporated exception handling in player class
    abstract class Player1 extends Person {
        private Scanner input;

        public Player1() {
            input = new Scanner(System.in);
        }
        //method for the player1 - task that needs to be completed
        //the user's task is to pick their stance (choose either rock, paper, or scissors)
        //implemented method from Person class
        public void selectStance() {
            System.out.println("Welcome Player, Type in the following choices: P - Paper, S - Scissors, or R - Rock");
            setStance(input.nextLine().toUpperCase()); //Handle incorrect capitalization of otherwise valid user input
            System.out.println("Enjoy the Game!");
            System.out.println("Please be sure to rate it when done!");
        }










    }
//    public Player1(String firstName, double score) {
//        super(firstName, score);
//    }
//}
