package game.level.generateLevel;

import game.level.Level;
import game.tools.Pos;

import java.util.Random;

import static game.Game.*;

/**
 * LevelGenerator
 * 
 * This class is used to generate a level.
 */
public class LevelGenerator {
    /**
     * cols, the number of columns
     * rows, the number of rows
     */
    private int cols, rows;

    /**
     * Constructor
     * 
     * @param cols the number of columns
     * @param rows the number of rows
     */
    public LevelGenerator(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    /**
     * This method generates a level, by adding the limits, the player, the monster, the treasure and the exit.
     * 
     * @return the level
     */
    public char[][] generateLevel() {
        char[][] level = new char[cols][rows];

        // create the limits of the levels (to be sure the player can't go out of bounds)
        level = createLimits(level);

        // add the entities
        level = addEntity(level, PLAYER);
        level = addEntity(level, MONSTER);
        level = addEntity(level, TREASURE);
        level = addEntity(level, EXIT);

        return level; // return the level
    }

    /**
     * This method creates the limits of the level.
     * 
     * @param level the level
     * @return the level with the limits
     */
    private char[][] createLimits(char[][] level) {
        // create the limits of the level
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                // if it's a limit, put a wall
                if (x == 0 || y == 0 || x == cols - 1 || y == rows - 1) {
                    level[x][y] = WALL;
                } else { // if it's not a limit, put an empty space
                    level[x][y] = ' ';
                }
            }
        }

        return level; // return the level
    }

    /**
     * This method adds an entity to the level.
     * 
     * @param level the level
     * @param entityType the entity type
     * @return the level with the entity
     */
    private char[][] addEntity(char[][] level, char entityType) {
        // get the number of empty space in the level
        int nbrEmptySpace = Level.howManyEmpty(level);

        // create a Random object
        Random rand = new Random();
        // generate a random number between 1 and the number of empty space
        int rand_int = rand.nextInt(nbrEmptySpace - 1) + 1;

        // get the position of the random number
        Pos playerPos = Level.spaceToPos(rand_int, level);

        // if the position is not null, add the entity
        if (playerPos != null) {
            level[playerPos.getX()][playerPos.getY()] = entityType;
        } else {
            // if the position is null, print an error message
            System.out.println("Error, couldn't add a player");
        }

        return level; // return the level if the new entity randomly added
    }
}
