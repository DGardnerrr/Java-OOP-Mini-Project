import java.util.Random;
//ComputerOrPlayer2Class inherited

public abstract class ComputerOrPlayer2 extends Player1 {
    //private variables for ComputerOrPlayer2 class
    private Random rand;
    private final int MAX_NUMBER = 3;

    //setter for one of variables to update new info
    public ComputerOrPlayer2() {
        setFirstigName("They");
        rand = new Random();
    }
    //override method for ComputerOrPlayer2 class - task that ComputerOrPlayer2class must perform
    //for the game to be executed
    public void selectStance() {
        int anyInteger = rand.nextInt(MAX_NUMBER);
        int randomNumber = 0;
        switch(randomNumber) {
            case 0:
                setStance("ROCK");
            case 1:
                setStance("PAPER");
            case 2:
                setStance("SCISSORS");
                break;
        }

    }}
