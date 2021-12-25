package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//abstract player class could not be instantiated

//class - Main class - used to instantiate object
public class Main {
    public static void main(String[] args) {
        // Creating object of RockPaperScissorGame class/instantiate
        new RockPaperScissorGame();

    }
}


// class - RockPaperScissorGame

class RockPaperScissorGame {
    // static variable keeps a track of game status for the entire program
    public static boolean isRunning;
    Scanner sc;
//created array list of game history
    private ArrayList<Integer> gameHistory;
    private Player player1;
    private Player player2;

    // Constructor used to initialize objects
    RockPaperScissorGame() {
        isRunning = true;
        gameHistory = new ArrayList<>();
        sc = new Scanner(System.in);
        start();
    }

    // getter for player name
    private String getPlayerName() {
        sc.nextLine();
        System.out.print("Enter player name ---- ");
        return sc.nextLine();
    }
    //getter
    private int getWinner() {
        // 1 -> player1 wins, 2 -> player2 wins, 0 -> tie
        int p1Choice = player1.getChoice(), p2Choice = player2.getChoice();
        if(p1Choice == p2Choice) {
            return 0;
        }
        else if(p1Choice == 0) {
            if(p2Choice == 2) {
                return 1;
            } else {
                return 2;
            }
        }
        else if(p1Choice == 1) {
            if(p2Choice == 2) {
                return 2;
            } else {
                return 1;
            }
        }
        else {
            if(p2Choice == 0) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    // Reused to take yes or no input
    //ensured  there is no incorrect user input
    private char getYorN(String msg) {
        char choice = ' ';
        while(Character.toUpperCase(choice) != 'Y' || Character.toUpperCase(choice) != 'N') {
            try {
                System.out.println("\n"+msg);
                System.out.print("Press y/n: ");
                choice = Character.toUpperCase(sc.next().charAt(0));
                if(choice == 'Y' || choice == 'N') {
                    return choice;
                } else {
                    System.out.println("Invalid Choice! Press y/n...\n");
                }
            } catch(Exception e) {
                System.out.println("Invalid Choice! Press y/n...\n");
            }
        }
        return choice;
    }
    // private method to play again
    private void playAgain() {
        if(getYorN("Do you wanna play again?") == 'Y') {
            playGame();
        }
    }

    //private method to view the history
    private void viewHistory() {
        if(getYorN("Do you wanna view history?") == 'Y')
            showHistory();
    }
    //private method to show the game history
    private void showHistory() {
        int p1c = 0, p2c = 0, game = 0;
        System.out.println();
        System.out.printf("%15s %15s %15s", "Game", player1.getName(), player2.getName());
        System.out.println();
        for (int i : gameHistory) {
            if(i == 0) {
                System.out.printf("%15d %15s", game++, PlayerState.Tie);
            } else if(i == 1) {
                System.out.printf("%15s %15s %15s", game++, PlayerState.Win, PlayerState.Lose);
            } else {
                System.out.printf("%15s %15s %15s", game++, PlayerState.Lose, PlayerState.Win);
            }
            System.out.println();
        }
    }
   //private method to show the winner
    private void showWinner() {
        int winner = getWinner();
        System.out.println();
        switch (winner) {
            case 0: System.out.println("Tie");
                break;
            case 1: System.out.println(player1.getName()+"\t" + "Wins");
                break;
            default: System.out.println(player1.getName()+"\t" + "Wins");
                break;
        }
        gameHistory.add(winner);
    }
    //private method to play the game
    private void playGame() {
        player1.selectChoice();
        player2.selectChoice();
        player1.displayChoices();
        player2.displayChoices();
        showWinner();
        playAgain();
    }
    //private method to reset game
    private void resetGame() {
        isRunning = true;
        gameHistory.clear();
    }

    // private method starts the game
    private void start() {
        System.out.println("Hi! Let's start Rock Paper Scissor Game\n");
        while(isRunning) {
            System.out.println();
            System.out.println("1. Two players");
            System.out.println("2. Vs Computer");
            System.out.println("3. Exit Game\n");
            System.out.print("Enter choice ---- ");
            // used try catch blocks for invalid input formats
            try {
                int choice = sc.nextInt();
                if (choice == 3) {
                    isRunning = false;
                    break;
                } else if(choice == 2) {
                    player1 = new User(getPlayerName());
                    player2 = new Computer();
                    playGame();
                    viewHistory();
                } else if(choice == 1) {
                    player1 = new User(getPlayerName());
                    player2 = new User(getPlayerName());
                    playGame();
                    viewHistory();
                } else {
                    throw new Exception("Invalid Choice! Enter 1, 2 or 3...\n");   //use exception to ensure game crashes gracefully
                }
                resetGame();
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("Invalid Choice! Enter 1, 2 or 3...\n");
            }
            // used sc.nextLine() and one can see, i have not stored its value anywhere
            // this is because, it enters after sc.nextInt() is stored in this
            sc.nextLine();
        }
    }
}

// Parent class used for creating child classes user and computer/second player
abstract class Player {
    // protected variables makes it accessible only to child class
    protected String name;
    protected int choice;

    // setter or mutator/getter for name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    // setter or mutator/getter for getChoice
    public int getChoice() {
        return choice;
    }
    public void setChoice(int choice) {
        this.choice = choice;
    }
    //used getter to update choices
    private String getChoiceName(int choice) {
        switch(choice) {
            case 1:
            {
                return "ROCK";
            }
            case 2:
            {
                return "PAPER";
            }
            default:
            {
                return "SCISSOR";
            }
        }
    }
    //protected method
    protected void displayChoices() {
        System.out.println("\n"+getName() + "\nChose-> "+ getChoiceName(getChoice()));
    }

    // using abstract method whose definition will be mentioned in child class
    protected abstract void selectChoice();
}

//Class User extended the player class
//User class
class User extends Player{
    private Scanner sc;

    User(String name) {
        // super keyword used to access field of parent class
        super.name = name;
        sc = new Scanner(System.in);
    }
    //public method
    public void selectChoice() {
        String choice = "";
        // this method ignores cases, prevents invalid user input
        while(!choice.equalsIgnoreCase("ROCK") || !choice.equalsIgnoreCase("PAPER") || !choice.equalsIgnoreCase("SCISSOR")) {
            System.out.print("\nEnter rock, paper or scissor ---- ");
            choice = sc.nextLine();
            if(choice.equalsIgnoreCase("ROCK")) {
                setChoice(1);
                break;
            } else if(choice.equalsIgnoreCase("PAPER")) {
                setChoice(2);
                break;
            } else if(choice.equalsIgnoreCase("SCISSOR")) {
                setChoice(3);
                break;
            } else {
                System.out.println("Invalid Choice! Enter rock, paper or scissor...\n");
            }
        }
    }
}
  //Computer class extends abstract player class
class Computer extends Player{
    private Random r;

    Computer() {
        setName("Computer");
        r = new Random();
    }
      //public method
    public void selectChoice() {
        // here r.nextInt(3) can generate 0,1 or 2, therefore added 1 which increases the value by 1
        // as per our code requirement
        setChoice(r.nextInt(3) + 1);
    }
}

// enum used instead of writing tie or win repeatedly
enum PlayerState {
    Tie, Win, Lose;
}