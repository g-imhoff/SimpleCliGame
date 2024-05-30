import game.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.util.Scanner;

import static game.Game.GAME_RESTART;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int result = GAME_RESTART;
        Scanner scanUserInput = new Scanner(System.in);
        while (result == GAME_RESTART) {
            Game g = new Game(scanUserInput);
            g.launchStartingPage();
            result = g.play();
        }

    }
}