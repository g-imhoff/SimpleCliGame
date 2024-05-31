package game.level.entities;

import game.tools.Pos;
import game.level.Level;

import java.util.List;

/**
 * Entities
 * 
 * This interface is used to manage the entities.
 * 
 * The entities are represented by the characters 'E' (EXIT const), 'P' (DOOR const), 'T' (TREASURE const) and 'M' (MONSTER const) in the level file.
 */
public interface Entities {
    /**
     * Each entities has a list of all the entities position
     */
    List<Pos> allEntities = null;

    /**
     * This method checks if a position is an entity.
     * 
     * @param p the position to check
     * @return true if the position is an entity, false otherwise
     */

    Boolean isit(Pos p);

    /**
     * This method returns all the entities positions.
     * 
     * @param l the level
     * @return a list of all the entities positions
     */

    static List<Pos> allPos (Level l) { return null; }

    /**
     * This method returns all the entities positions.
     * 
     * @return a list of all the entities positions
     */
    
    List<Pos> getAllPos();

}
