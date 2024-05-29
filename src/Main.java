import game.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.IOException;
import java.util.Scanner;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanUserInput = new Scanner(System.in);
        Game g = new Game(scanUserInput);
        g.launchStartingPage();
        g.play();
    }
}