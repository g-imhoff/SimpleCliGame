package game;

import game.level.Level;
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


    private Level l = new Level();
    private Player p = new Player();

    private Scanner scanUserInput;

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
        System.out.println("Press " + UP + " to move up, " + DOWN + " to move down, " + LEFT + " to move left, " + RIGHT + " to move right, " + LEAVE + " to exit");
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

    public void initAllPos() {
        Pos playerPositon = p.whereIsPlayer(l.getLevel());
        p.setPos(playerPositon);
    }

    public void play() {
        char inputLine;

        while (!(p.onExit())) {
            clearScreen();
            l.printLevel();
            printKeys();

            System.out.print("What do you want to do : ");
            inputLine = scanUserInput.next().charAt(0);

            switch (inputLine) {
                case UP:
                    System.out.println(UP);
                    break;

                case DOWN:
                    System.out.println(DOWN);
                    break;

                case LEFT:
                    System.out.println(LEFT);
                    break;

                case RIGHT:
                    System.out.println(RIGHT);
                    break;

                case LEAVE :
                    launchStartingPage();
                    break;
            }
        }
    }
}
