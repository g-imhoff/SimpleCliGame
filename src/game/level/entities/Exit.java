package game.level.entities;

import game.level.Level;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;

import static game.Game.EXIT;

/**
 * Exit
 * 
 * This class is used to manage the Exit entities.
 * 
 * The Exit entities are represented by the character 'E' (EXIT const) in the level file.
 */
public class Exit implements Entities {
    /**
     * allEntities, a list of all the entities position
     */
    List<Pos> allEntities = new ArrayList<Pos>();

    /**
     * Constructor
     * 
     * @param level the level array
     */
    public Exit(char[][] level) {
        // search all the EXIT entities position in the level
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's an EXIT, put its position in allEntities
                if (level[i][j] == EXIT) {
                    Pos p = new Pos(i, j);
                    allEntities.add(p);
                }
            }
        }
    }

    /**
     * This method checks if a position is an Exit.
     * 
     * @param p the position to check
     * @return true if the position is an Exit, false otherwise
     */
    public Boolean isit(Pos p) {
        // check if there is a position with the same coordinates
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true; // if it's an Exit, return true
        }

        return false; // if it's not an Exit, return false
    }

    /**
     * This method returns all the Exit positions in a specific level.
     * 
     * @param l the level
     * @return a list of all the Exit positions
     */
    static List<Pos> allPos (Level l) {
        // get the level array
        char[][] level = l.getLevel();

        // create a list of all the Exit positions
        List<Pos> all  = new ArrayList<Pos>();
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's an Exit, put its position in all to fill the list
                if (level[i][j] == EXIT) {
                    Pos p = new Pos(i, j);
                    all.add(p);
                }
            }
        }

        return all; // return the list of all the Exit positions
    }

    /**
     * This method returns all the Exit positions.
     * 
     * @return a list of all the Exit positions
     */
    public List<Pos> getAllPos() {
        return allEntities; // return the list of all the Exit positions
    }

    /**
     * This method checks if the player is on an Exit.
     * 
     * @param playerPos the player position
     * @return true if the player is on an Exit, false otherwise
     */
    public Boolean onExit(Pos playerPos) {
        // check if there is a position with the same coordinates in allEntities
        for (Pos Entity : allEntities) {
            if (Entity.getX() == playerPos.getX() && Entity.getY() == playerPos.getY()) {
                return true; // if the player is on an Exit, return true
            }
        }

        return false; // if the player is not on an Exit, return false
    }
}
