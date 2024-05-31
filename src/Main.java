import game.Game;

import java.util.Scanner;

import static game.Game.GAME_RESTART;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int result = GAME_RESTART;

        // create a scanner to read the user input (the same for the whole program)
        Scanner scanUserInput = new Scanner(System.in);

        // while the user wants to restart the game, we always send him to the launching page and then play the game
        while (result == GAME_RESTART) {
            Game g = new Game(scanUserInput);
            g.launchStartingPage();
            result = g.play();
        }
    }
}