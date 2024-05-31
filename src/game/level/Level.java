package game.level;

import game.level.chooseLevel.LevelChooser;
import game.level.chooseLevel.LevelReader;
import game.level.entities.Door;
import game.level.entities.Exit;
import game.level.entities.Monster;
import game.level.entities.Treasure;
import game.level.generateLevel.LevelGenerator;
import game.player.Player;
import game.tools.Pos;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static game.Game.*;

public class Level {
    private char[][] level;

    private Player p;
    private Door d;
    private Treasure t;
    private Exit e;
    private Monster m;

    private Pos startPos;

    public Level() {}

    public void chooseLevel(Scanner scanUserInput) {
        LevelChooser levelChooser = new LevelChooser();
        levelChooser.choose(scanUserInput);
        String levelPath = levelChooser.getFinalLevelPath();
        LevelReader levelReader = new LevelReader(levelPath);
        level = levelReader.readLevel();
        if (level != null) {
            p = new Player(level);
            startPos = p.getPos();
            d = new Door(level);
            t = new Treasure(level);
            e = new Exit(level);
            m = new Monster(level);
        }
    }

    public void generateLevel() {
        Random rand = new Random();

        int cols = rand.nextInt(MAX_COLS_SIZE - MIN_COLS_SIZE) + MIN_COLS_SIZE;
        int rows = rand.nextInt(MAX_ROWS_SIZE - MIN_ROWS_SIZE) + MIN_ROWS_SIZE;

        LevelGenerator generator = new LevelGenerator(cols, rows);
        level = generator.generateLevel();

        if (level != null) {
            p = new Player(level);
            startPos = p.getPos();
            d = new Door(level);
            t = new Treasure(level);
            e = new Exit(level);
            m = new Monster(level);
        }
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
                return GAME_LEFT;

        }

        List<Pos> allDoorPos = d.getAllPos();

        allDoorPos.forEach((doorPos) -> {
            if (level[doorPos.getX()][doorPos.getY()] == ' ') {
                level[doorPos.getX()][doorPos.getY()] = 'P';
            }
        });

        if (e.onExit(p.getPos())) return WON;

        return PLAYING;
    }

    public Player getP() {
        return p;
    }

    public Door getD() {
        return d;
    }

    public Treasure getT() {
        return t;
    }

    public Monster getM() {
        return m;
    }

    public Pos getStartPos() {
        return startPos;
    }

    public static int howManyEmpty(char[][] level) {
        int emptySpace = 0;
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ') emptySpace++;
            }
        }

        return emptySpace;
    }

    public static Pos spaceToPos(int numberSpace, char[][] level) {
        int count = 0;

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ') count++;

                if(count == numberSpace) {
                    Pos p = new Pos(i, j);

                    return p;
                }
            }
        }

        return null;
    }
}
