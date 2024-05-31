package game.level.entities;

import game.level.Level;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;

import static game.Game.DOOR;

/**
 * Door
 * 
 * This class is used to manage the Door entities.
 * 
 * The Door entities are represented by the character 'P' (DOOR const) in the level file.
 */

public class Door implements Entities {
    /**
     * allEntities, a list of all the entities position
     */
    List<Pos> allEntities = new ArrayList<Pos>(); //all the entities position is here

    /**
     * Constructor
     * 
     * @param level the level array
     */
    public Door(char[][] level) {
        // search all the entities position
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == DOOR) {
                    Pos p = new Pos(i, j);
                    allEntities.add(p); // if it's a Door, put is position in allEntities
                }
            }
        }
    }

    /**
     * This method checks if a position is a Door.
     * 
     * @param p the position to check
     * @return true if the position is a Door, false otherwise
     */

    public Boolean isit(Pos p) {
        //check if there is a position with the same coordinates
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true;
        }

        return false;
    }

    /**
     * This method returns all the Door positions in a specific level.
     * 
     * @param l the level
     * @return a list of all the Door positions
     */

    public static List<Pos> allPos (Level l) {
        // get the level
        char[][] level = l.getLevel();

        // create a list
        List<Pos> all  = new ArrayList<Pos>();

        // search all the entities position
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                //if it's a Door, put is position in all to fill the list
                if (level[i][j] == DOOR) {
                    Pos p = new Pos(i, j);
                    all.add(p);
                }
            }
        }

        return all; // return the list with all the position
    }

    /**
     * This method returns all the Door positions.
     * 
     * @return a list of all the Door positions
     */

    public List<Pos> getAllPos() {
        return allEntities; // return the list with all the position
    }
}
