package game.level;

import game.level.chooseLevel.LevelChooser;
import game.level.chooseLevel.LevelReader;
import game.level.entities.Door;
import game.level.entities.Treasure;
import game.player.Player;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static game.Game.*;

public class Level {
    private char[][] level;

    private Player p;
    private Door d;
    private Treasure t;
    
    public Level() {}

    public void chooseLevel(Scanner scanUserInput) {
        LevelChooser levelChooser = new LevelChooser();
        levelChooser.choose(scanUserInput);
        String levelPath = levelChooser.getFinalLevelPath();
        LevelReader levelReader = new LevelReader(levelPath);
        level = levelReader.readLevel();
        p = new Player(level);
        d = new Door(level);
        t = new Treasure(level);
    }

    public void generateLevel() {

    }

    public char[][] getLevel() {
        return level;
    }

    public void setLevel(char[][] level) { this.level = level; }

    public char returnVal(Pos p) {
        return level[p.getX()][p.getY()];
    }

    public void printLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }

    public int move(Scanner scanUserInput) {
        System.out.print("What do you want to do : ");
        char inputLine = scanUserInput.next().charAt(0);

        switch (inputLine) {
            case UP:
                setLevel(p.move(new Pos(-1, 0), level));
                break;

            case DOWN:
                setLevel(p.move(new Pos(1, 0), level));
                break;

            case LEFT:
                setLevel(p.move(new Pos(0, -1), level));
                break;

            case RIGHT:
                setLevel(p.move(new Pos(0, 1), level));
                break;

            case LEAVE:
                //means that we did'nt finish the game, but just left the game
                return -1;

        }

        List<Pos> allDoorPos = d.getAllPos();

        allDoorPos.forEach((doorPos) -> {
            if (level[doorPos.getX()][doorPos.getY()] == ' ') {
                level[doorPos.getX()][doorPos.getY()] = 'P';
            }
        });

        return 0;
    }

    public Player getP() {
        return p;
    }

    public Door getD() {
        return d;
    }
}
