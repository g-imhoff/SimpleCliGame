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

/**
 * Level
 * <p>
 * This class is used to manage the level.
 */
public class Level {
    /**
     * level, the level array, a 2D array of characters
     */
    private char[][] level;

    /**
     * p, the player
     * d, the door
     * t, the treasure
     * e, the exit
     * m, the monster
     */
    private Player p;
    private Door d;
    private Treasure t;
    private Exit e;
    private Monster m;

    /**
     * Constructor
     */
    public Level() {
    }

    /**
     * This method chooses a level.
     *
     * @param scanUserInput the scanner to read the user input
     */
    public void chooseLevel(Scanner scanUserInput) {
        // choose the level
        LevelChooser levelChooser = new LevelChooser();
        levelChooser.choose(scanUserInput);

        // read the level
        String levelPath = levelChooser.getFinalLevelPath();
        LevelReader levelReader = new LevelReader(levelPath);
        level = levelReader.readLevel();

        // if the level is not null (didn't got an error when reading the level), create the entities
        if (level != null) {
            // create the entities
            p = new Player(level);
            d = new Door(level);
            t = new Treasure(level);
            e = new Exit(level);
            m = new Monster(level);
        }
    }

    /**
     * This method generates a level.
     */
    public void generateLevel() {
        // generate a Random object
        Random rand = new Random();

        // generate a random number of columns and rows (respecting the minimum and maximum size)
        int cols = rand.nextInt(MAX_COLS_SIZE - MIN_COLS_SIZE) + MIN_COLS_SIZE; // generate a random number between MIN_COLS_SIZE and MAX_COLS_SIZE
        int rows = rand.nextInt(MAX_ROWS_SIZE - MIN_ROWS_SIZE) + MIN_ROWS_SIZE; // generate a random number between MIN_ROWS_SIZE and MAX_ROWS_SIZE

        // generate the level
        LevelGenerator generator = new LevelGenerator(cols, rows);
        level = generator.generateLevel();

        // if the level is not null (didn't got any error) create the entities
        if (level != null) {
            // create the entities
            p = new Player(level);
            d = new Door(level);
            t = new Treasure(level);
            e = new Exit(level);
            m = new Monster(level);
        }
    }

    /**
     * This method returns the level.
     *
     * @return the level
     */
    public char[][] getLevel() {
        return level;
    }

    /**
     * This method sets the level.
     *
     * @param level the level
     */
    public void setLevel(char[][] level) {
        this.level = level;
    }

    /**
     * This method returns the value of a position in the level.
     *
     * @param p the position
     * @return the value of the position
     */
    public char returnVal(Pos p) {
        return level[p.getX()][p.getY()];
    }

    /**
     * This method prints the level.
     */
    public void printLevel() {
        // print the level by going through all the positions
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }

    /**
     * This method moves the player.
     *
     * @param scanUserInput the scanner to read the user input
     * @return the game state
     */
    public int move(Scanner scanUserInput) {
        // ask the user what he wants to do
        System.out.print("What do you want to do : ");
        char inputLine = scanUserInput.next().charAt(0);

        // move the player
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

        // in case the player or a monster overwrites the DOOR, we need to print it back
        List<Pos> allDoorPos = d.getAllPos();

        allDoorPos.forEach((doorPos) -> {
            if (level[doorPos.getX()][doorPos.getY()] == ' ') {
                level[doorPos.getX()][doorPos.getY()] = DOOR;
            }
        });

        // in case the player in on an EXIT, you won
        if (e.onExit(p.getPos())) return WON;

        // return the game state playing, if we reach this return, it means that the game is still playing
        return PLAYING;
    }

    /**
     * This method give you the possibilty to get the player.
     *
     * @return the player
     */
    public Player getP() {
        return p;
    }

    /**
     * This method give you the possibilty to get the door.
     *
     * @return the door
     */

    public Door getD() {
        return d;
    }

    /**
     * This method give you the possibilty to get the treasure.
     *
     * @return the treasure
     */
    public Treasure getT() {
        return t;
    }

    /**
     * This method give you the possibilty to get the monster.
     *
     * @return the monster
     */
    public Monster getM() {
        return m;
    }

    /**
     * This method give you the number of empty space in the level.
     *
     * @param level the level
     * @return the number of empty space
     */
    public static int howManyEmpty(char[][] level) {
        // count the number of empty space in the level
        int emptySpace = 0;
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's an empty space, increment the counter
                if (level[i][j] == ' ') emptySpace++;
            }
        }

        return emptySpace; // return the number of empty space
    }

    /**
     * This method returns the position of a specific space in the level.
     *
     * @param numberSpace the number of the space
     * @param level       the level
     * @return the position of the space
     */
    public static Pos spaceToPos(int numberSpace, char[][] level) {
        // count the number of empty space
        int count = 0;

        // go through all the positions until we reach the number of the space
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ') count++;

                // if we reach the number of the space, return the position
                if (count == numberSpace) {
                    Pos p = new Pos(i, j);

                    return p;
                }
            }
        }

        return null; // return null if we didn't find the position
    }
}
