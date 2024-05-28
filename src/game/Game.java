package game;

import game.level.Level;
import java.util.Scanner;

public class Game {
    public static final int MAX_LEVELS = 256;
    public static final char WALL = '#';
    public static final char PLAYER = 'J';
    public static final char TREASURE = 'T';
    public static final char EXIT = 'S';
    public static final char DOOR = 'P';

    private Level l = new Level();

    public Game() {}

    public void launchStartingPage() {
        // Used to read the input
        try (Scanner scanUserInput = new Scanner(System.in)) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        play();
    }

    public void chooseLevel() {
        l.chooseLevel();
    }

    public void generateLevel() {
        l.generateLevel();
    }

    public void exitGame() {
        System.exit(0);
    }

    public void play() {
        if (l.getLevel() != null) {
            System.out.println(1);
        }
    }
}
