package game.level.entities;

import game.level.Level;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.Game.TREASURE;

/**
 * Treasure
 * <p>
 * This class is used to manage the Treasure entities.
 * <p>
 * The Treasure entities are represented by the character 'T' (TREASURE const) in the level file.
 */
public class Treasure implements Entities {
    /**
     * allEntities, a list of all the entities position
     */
    List<Pos> allEntities = new ArrayList<Pos>();

    /**
     * Constructor
     *
     * @param level the level array
     */
    public Treasure(char[][] level) {
        // search all the entities position
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's an Treasure, put its position in allEntities
                if (level[i][j] == TREASURE) {
                    Pos p = new Pos(i, j);
                    allEntities.add(p);
                }
            }
        }
    }

    /**
     * This method checks if a position is an Treasure.
     *
     * @param p the position to check
     * @return true if the position is an Treasure, false otherwise
     */
    public Boolean isit(Pos p) {
        // check if there is a position with the same coordinates
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true; // if it's an Treasure, return true
        }

        return false; // if it's not an Treasure, return false
    }

    /**
     * This method returns all the Treasure positions in a specific level.
     *
     * @param l the level
     * @return a list of all the Treasure positions
     */
    static List<Pos> allPos(Level l) {
        // get the level
        char[][] level = l.getLevel();

        // create a list of all the Treasure positions
        List<Pos> all = new ArrayList<Pos>();

        // search all the Treasure entities position in the level
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's an Treasure, put its position in allEntities
                if (level[i][j] == TREASURE) {
                    Pos p = new Pos(i, j);
                    all.add(p);
                }
            }
        }

        return all; // return the list
    }

    /**
     * This method returns all the Treasure positions.
     *
     * @return a list of all the Treasure positions
     */
    public List<Pos> getAllPos() {
        return allEntities; // return the list of all the Treasure positions
    }

    /**
     * This method generates a new Treasure in the level.
     * This method don't create always a new Treasure, it's random. (10% chance to create a new Treasure)
     *
     * @param level the level array
     * @return the level array with the new Treasure
     */
    public char[][] randomNewTreasure(char[][] level) {
        // create a Random object
        Random rand = new Random();

        // generate a random number between 0 and 100
        int rand_int = rand.nextInt(101);

        // if the random number is greater or equal than 90, create a new Treasure
        if (rand_int >= 90) {
            // get the number of empty space in the level
            int emptySpace = Level.howManyEmpty(level);
            // generate a random number between 1 and the number of empty space
            int rand_int2 = rand.nextInt(emptySpace - 1) + 1;
            // get the position of the new Treasure
            Pos posNewTreasure = Level.spaceToPos(rand_int2, level);

            // if the position is not null, add the new Treasure
            if (posNewTreasure != null) {
                level = addTreasure(posNewTreasure, level);
                allEntities.add(posNewTreasure);
            } else {
                // if the position is null, print an error message
                System.out.println("Error: the position of a new Treasure couldn't be found.");
            }
        }

        return level; // return the level array with the new Treasure (or not if the random number is less than 90)
    }

    /**
     * This method adds a Treasure in the level.
     *
     * @param p     the position of the new Treasure
     * @param level the level array
     * @return the level array with the new Treasure
     */
    public char[][] addTreasure(Pos p, char[][] level) {
        level[p.getX()][p.getY()] = 'T';
        return level;
    }

    /**
     * This method checks if the player is on a Treasure.
     *
     * @param playerPos the player position
     * @return true if the player is on a Treasure, false otherwise
     */
    public Boolean onTreasure(Pos playerPos) {
        // check if there is a position with the same coordinates in allEntities
        // get the index of the position, needed to remove the Treasure
        int index = 0;

        // check if the player is on a Treasure
        for (Pos Entity : allEntities) {
            // if the player is on a Treasure, remove the Treasure and return true
            if (Entity.getX() == playerPos.getX() && Entity.getY() == playerPos.getY()) {
                allEntities.remove(index);
                return true;
            }

            index++;
        }

        return false; // if the player is not on a Treasure, return false
    }

    /**
     * This method prints the Treasure in the level.
     * Sometimes, monster can be overwriten on the Treasure, when the monster moves, we need to print back the Treasure.
     *
     * @param level the level array
     * @return the level array with the Treasure
     */
    public char[][] printBack(char[][] level) {
        // print the Treasure in the level
        for (Pos Entity : allEntities) {
            // if the position is empty (necessary to set this condition to not overwrite the treasure on a monster for exemple), print the Treasure
            if (level[Entity.getX()][Entity.getY()] == ' ') {
                level[Entity.getX()][Entity.getY()] = 'T'; // print the Treasure back
            }
        }

        return level; // return the level array, we are sure that we have all the Treasure in the level
    }
}
