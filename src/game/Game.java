package game;

import java.util.Scanner;

public class Game {
    public Game() {

    }

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
    }

    public void chooseLevel() {
        System.out.println("Choose a level");
    }

    public void generateLevel() {
        System.out.println("Generate a level");
    }

    public void exitGame() {
        System.out.println("Exit game");
    }
}
