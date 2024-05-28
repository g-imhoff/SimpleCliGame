import game.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.IOException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Game g = new Game();
        g.launchStartingPage();
        g.initAllPos();
        g.play();
    }
}