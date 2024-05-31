package game;

import game.level.Level;

import java.util.Scanner;

/**
 * Game
 * 
 * This class is used to manage the game.
 */
public class Game {
    /**
     * MAX_LEVELS, the maximum number of levels
     * WALL, the wall character
     * PLAYER, the player character
     * TREASURE, the treasure character
     * EXIT, the exit character
     * DOOR, the door character
     * MONSTER, the monster character
     * 
     * UP, the up key
     * DOWN, the down key
     * LEFT, the left key
     * RIGHT, the right key
     * NOMOVE, used to say that you don't move
     * KILLED, used to say that you got killed
     * LEAVE, used to say that you left the game
     * 
     * WON, used to say that you won the game
     * PLAYING, used to say that you are playing
     * GAME_LEFT, used to say that you left the game
     * LOST, used to say that you lost the game
     * 
     * YES, the yes key
     * NO, the no key
     * 
     * GAME_RESTART, used to say that you want to restart the game
     * GAME_OVER, used to say that the game is over
     * GAME_END_ERROR, used to say that the game ended with an error
     * GAME_INPUT_USER_ERROR, used to say that the user input create an error
     * 
     * MIN_ROWS_SIZE, the minimum number of rows
     * MIN_COLS_SIZE, the minimum number of columns
     * MAX_ROWS_SIZE, the maximum number of rows
     * MAX_COLS_SIZE, the maximum number of columns
     * 
     * l, the level
     * 
     * numberTreasureCollect, the number of treasure collected
     * numberMonsterKilled, the number of monster killed
     * 
     * scanUserInput, the scanner to read the user input
     * 
     */
    public static final int MAX_LEVELS = 256;
    public static final char WALL = '#';
    public static final char PLAYER = 'J';
    public static final char TREASURE = 'T';
    public static final char EXIT = 'S';
    public static final char DOOR = 'P';
    public static final char MONSTER = 'M';

    public static final char UP = 'z';
    public static final char DOWN = 's';
    public static final char LEFT = 'q';
    public static final char RIGHT = 'd';
    public static final char NOMOVE = 'n';
    public static final char KILLED = 'k';
    public static final char LEAVE = 'e';

    public static final int WON = 1;
    public static final int PLAYING = 0;
    public static final int GAME_LEFT = -1;
    public static final int LOST = -2;
    public static final int GAME_LEVEL_ISSUE_SIZE = -3;

    public static final char YES = 'y';
    public static final char NO = 'n';

    public static final int GAME_RESTART = 0;
    public static final int GAME_OVER = 1;
    public static final int GAME_END_ERROR = 2;
    public static final int GAME_INPUT_USER_ERROR = 3;

    private Level l = new Level();

    private int numberTreasureCollect = 0;
    private int numberMonsterKilled = 0;

    public static final int MIN_ROWS_SIZE = 4;
    public static final int MIN_COLS_SIZE = 4;
    public static final int MAX_ROWS_SIZE = 16;
    public static final int MAX_COLS_SIZE = 16;

    private final Scanner scanUserInput;

    /**
     * Constructor
     * 
     * @param scanUserInput the scanner to read the user input
     */
    public Game(Scanner scanUserInput) {
        this.scanUserInput = scanUserInput;
    }

    /**
     * This method launches the starting page.
     */
    public void launchStartingPage() {
        // Show user what he can choose
        System.out.println("1 - Choose a level");
        System.out.println("2 - Generate a level");
        System.out.println("3 - Exit game");

        // Ask user what he wants to do
        System.out.print("What do you want to do : ");
        int inputLine = scanUserInput.nextInt();
        scanUserInput.nextLine(); // to be sure everything after the number is read

        // Do what the user wants
        switch (inputLine) {
            case 1:
                chooseLevel();
                break;

            case 2:
                generateLevel();
                break;

            case 3:
                exitGame();
                break;

            default :
                System.out.println("This key doesnt mean anything");
                System.exit(0);
                break;

        }
    }

    /**
     * This method prints the keys.
     */
    public void printKeys() {
        System.out.println("Press " + UP + " to move up, " + DOWN + " to move down, " + LEFT + " to move left, " + RIGHT + " to move right, " + LEAVE + " to exit");
    }

    /**
     * This method prints the yes and no keys.
     */
    public void printYesNo() {
        System.out.print("Press " + YES + " to say yes, " + NO + " to say no : ");
    }

    /**
     * This method prints the number of treasure collected and monster killed.
     */
    public void printNumberTreasureCollect() {
        System.out.println("You collected " + numberTreasureCollect + " treasure and killed " + numberMonsterKilled + " monster");
    }

    /**
     * This method clears the screen.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * This method let ther user chooses a level.
     */
    public void chooseLevel() {
        l.chooseLevel(scanUserInput);
    }

    /**
     * This method generates a level.
     */
    public void generateLevel() {
        l.generateLevel();
    }

    /**
     * This method exits the game.
     */
    public void exitGame() {
        System.exit(0);
    }

    /**
     * This method plays the game.
     * 
     * @return the game state
     */
    public int play() {
        // set the current game state as PLAYING
        int result = PLAYING;

        // check if the level is null
        if (l.getLevel() == null) {
            result = GAME_LEVEL_ISSUE_SIZE; // set the game state as GAME_LEVEL_ISSUE_SIZE if level == null
        }

        // play the game
        while (result == PLAYING) {
            // clear the screen
            clearScreen();
            // print the level
            l.printLevel();
            // print the number of treasure collected and monster killed
            printNumberTreasureCollect();
            // print the keys
            printKeys();
            // move the player, change the game state after the player moved
            result = l.move(scanUserInput);
            // check if the player is on a treasure
            if (l.getT().onTreasure(l.getP().getPos())) numberTreasureCollect++;
            // check if the player is on a monster
            if (l.getM().onMonster(l.getP().getPos())) numberMonsterKilled++;
            // 10% chance to have a new treasure
            l.setLevel(l.getT().randomNewTreasure(l.getLevel()));
            // 80% for each monster to move 
            l.setLevel(l.getM().randomMonsterMove(l.getLevel(), l.getP().getPos()));
            // print the treasure back (in case monster overwrites some treasure)
            l.setLevel(l.getT().printBack(l.getLevel()));
            // if a monster is on the player, the player is killed
            if (l.getP().onPlayer(l.getM().getAllPos())) {
                // set the result as LOST
                result = LOST;
                // clear the screen and print the level in red
                clearScreen();
                System.out.print("\u001B[31m");
                l.printLevel();
                System.out.print("\u001B[37m");
            }
        }

        char userInput;

        // depending on the result, we will handle the game to do something, if there is no GAME_END_ERROR you are allowed to play again or choose to leave
        switch (result) {
            case GAME_LEFT:
                // ask the users
                System.out.println("You left the game, do you want to play again ?");
                printYesNo();
                userInput = scanUserInput.next().charAt(0);

                //handle the user input, depending on the user input, we restart a new game, leave the game, or print an error and leave the game
                if (userInput == YES) {
                    return GAME_RESTART;
                }
                else if (userInput == NO){
                    System.out.println("Goodbye !");
                    return GAME_OVER;
                }
                else  {
                    System.out.println("Error, this key doesnt mean anything");
                    return GAME_INPUT_USER_ERROR;
                }

            case WON:
                // ask the users
                System.out.println("You won game, do you want to play again ?");
                printYesNo();
                userInput = scanUserInput.next().charAt(0);

                //handle the user input, depending on the user input, we restart a new game, leave the game, or print an error and leave the game
                if (userInput == YES) {
                    return GAME_RESTART;
                }
                else if (userInput == NO) {
                    System.out.println("Well played and Goodbye !");
                    return GAME_OVER;
                }
                else {
                    System.out.println("Error, this key doesnt mean anything");
                    return GAME_INPUT_USER_ERROR;
                }

            case LOST :
                // ask the users
                System.out.println("You lost your game, do you want to play again ?");
                printYesNo();
                userInput = scanUserInput.next().charAt(0);

                //handle the user input, depending on the user input, we restart a new game, leave the game, or print an error and leave the game
                if (userInput == YES) {
                    return GAME_RESTART;
                }
                else if (userInput == NO) {
                    System.out.println("Try another time !");
                    return GAME_OVER;
                }
                else {
                    System.out.println("Error, this key doesnt mean anything");
                    return GAME_INPUT_USER_ERROR;
                }

            case GAME_LEVEL_ISSUE_SIZE:
                // ask the users
                System.out.println("Your level doesn't match the level size condition, do you want to choose another level ?");
                printYesNo();
                userInput = scanUserInput.next().charAt(0);

                //handle the user input, depending on the user input, we restart a new game, leave the game, or print an error and leave the game
                if (userInput == YES) {
                    return GAME_RESTART;
                }
                else if (userInput == NO) {
                    System.out.println("Sad to see you leave !");
                    return GAME_OVER;
                }
                else {
                    System.out.println("Error, this key doesnt mean anything");
                    return GAME_INPUT_USER_ERROR;
                }

            default :
                // if no result is found, we send an error
                return GAME_END_ERROR;

        }
    }
}
