package game;

import game.level.Level;
import game.level.entities.Door;
import game.player.Player;
import game.tools.Pos;

import java.util.Scanner;

public class Game {
    public static final int MAX_LEVELS = 256;
    public static final char WALL = '#';
    public static final char PLAYER = 'J';
    public static final char TREASURE = 'T';
    public static final char EXIT = 'S';
    public static final char DOOR = 'P';

    public static final char UP = 'z';
    public static final char DOWN = 's';
    public static final char LEFT = 'q';
    public static final char RIGHT = 'd';
    public static final char LEAVE = 'e';

    public static final int WON = 1;
    public static final int PLAYING = 0;
    public static final int GAME_LEFT = -1;

    public static final char YES = 'y';
    public static final char NO = 'n';

    private Level l = new Level();

    private final Scanner scanUserInput;

    public Game(Scanner scanUserInput) {
        this.scanUserInput = scanUserInput;
    }


    public void launchStartingPage() {
        // Used to read the input
        System.out.println("1 - Choose a level");
        System.out.println("2 - Generate a level");
        System.out.println("3 - Exit game");

        System.out.print("What do you want to do : ");

        int inputLine = scanUserInput.nextInt();

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

    public void printKeys() {
        System.out.print("Press " + UP + " to move up, " + DOWN + " to move down, " + LEFT + " to move left, " + RIGHT + " to move right : ");
    }

    public void printYesNo() {
        System.out.print("Press " + YES + " to say yes, " + NO + " to say no : ");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void chooseLevel() {
        l.chooseLevel(scanUserInput);
    }

    public void generateLevel() {
        l.generateLevel();
    }

    public void exitGame() {
        System.exit(0);
    }

    public void play() {
        int result = PLAYING;
        while (result == PLAYING) {
            clearScreen();
            l.printLevel();
            printKeys();
            result = l.move(scanUserInput);
            l.setLevel(l.getT().randomNewTreasure(l.getLevel()));
        }

        String userInput;

        switch (result) {
            case GAME_LEFT:
                System.out.println("You left the game, do you want to play again ?");
                printYesNo();
                userInput = scanUserInput.nextLine();
                if (!userInput.isEmpty()) {
                    if (userInput.charAt(0) == YES) launchStartingPage();
                    else if (userInput.charAt(0) == NO) System.out.println("Goodbye !");
                    else System.out.println("Erreur votre réponse n'existe pas !");
                }

                break;

            case WON:
                System.out.println("You won game, do you want to play again ?");
                printYesNo();
                userInput = scanUserInput.nextLine();

                if (userInput.charAt(0) == YES) launchStartingPage();
                else if (userInput.charAt(0) == NO) System.out.println("Well played and Goodbye !");
                else System.out.println("Erreur votre réponse n'existe pas !");
                break;

        }
    }
}
