package game.player;

import game.tools.Pos;

import java.util.List;

import static game.Game.PLAYER;
import static game.Game.WALL;

/**
 * Player
 * <p>
 * This class is used to manage the player.
 */
public class Player {
    /**
     * p, the player position
     */
    Pos p;

    /**
     * Constructor
     *
     * @param level the level array
     */
    public Player(char[][] level) {
        this.p = whereIsPlayer(level);
    }

    /**
     * This method returns the player position.
     *
     * @return the player position
     */
    public Pos getPos() {
        return p;
    }

    /**
     * This method sets the player position.
     *
     * @param p the new player position
     */
    public void setPos(Pos p) {
        this.p = p;
    }

    /**
     * This method returns the player position by searching in the level.
     *
     * @param level the level array
     * @return the player position
     */
    public static Pos whereIsPlayer(char[][] level) {
        // search the player position in the level
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's a player, return its position
                if (level[i][j] == PLAYER) {
                    Pos p = new Pos(i, j);
                    return p;
                }
            }
        }

        return null; // if there is no player, return null
    }

    /**
     * This method moves the player in the level.
     *
     * @param move  the move to do
     * @param level the level array
     * @return the new level array
     */
    public char[][] move(Pos move, char[][] level) {
        // get the new position of the player by adding the move
        Pos newPos = Pos.add(p, move);

        // if the new position is available (not a wall of things like that), move the player
        if (posAvailable(newPos, level)) {
            level[newPos.getX()][newPos.getY()] = PLAYER;
            level[p.getX()][p.getY()] = ' ';
            // set the new position
            p = newPos;
        }

        return level; // return the new level
    }

    /**
     * This method checks if a position is available.
     *
     * @param p     the position to check
     * @param level the level array
     * @return true if the position is available, false otherwise
     */
    public Boolean posAvailable(Pos p, char[][] level) {
        return level[p.getX()][p.getY()] != WALL && p.getX() >= 0 && p.getX() < level.length && p.getY() >= 0 && p.getY() < level[0].length;
    }

    /**
     * This method checks if the player is on a Door.
     *
     * @param allEntities the list of all the entities
     * @return true if the player is on a Door, false otherwise
     */
    public Boolean onPlayer(List<Pos> allEntities) {
        // check if there is a position with the same coordinates
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true; // if it's a Door, return true
        }

        return false; // if it's not a Door, return false
    }
}
