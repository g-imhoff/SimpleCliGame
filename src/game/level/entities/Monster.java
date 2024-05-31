package game.level.entities;

import game.level.Level;
import game.player.Player;
import game.tools.Pos;
import game.tools.aStar.AStarAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.Game.MONSTER;
import static game.Game.UP;
import static game.Game.DOWN;
import static game.Game.LEFT;
import static game.Game.RIGHT;
import static game.Game.KILLED;
/**
 * Monster
 * 
 * This class is used to manage the Monster entities.
 * 
 * The Monster entities are represented by the character 'M' (MONSTER const) in the level file.
 */
public class Monster implements Entities {
    /**
     * allEntities, a list of all the entities position
     */
    List<Pos> allEntities = new ArrayList<Pos>();

    /**
     * Constructor
     * 
     * @param level the level array
     */
    public Monster(char[][] level) {
        // search all the entities position
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's a Monster, put its position in allEntities
                if (level[i][j] == MONSTER) {
                    Pos p = new Pos(i, j);
                    allEntities.add(p);
                }
            }
        }
    }

    /**
     * This method checks if a position is a Monster.
     * 
     * @param p the position to check
     * @return true if the position is a Monster, false otherwise
     */
    public Boolean isit(Pos p) {
        // check if there is a position with the same coordinates
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true; // if it's a Monster, return true
        }

        return false; // if it's not a Monster, return false
    }

    /**
     * This method returns all the Monster positions in a specific level.
     * 
     * @param l the level
     * @return a list of all the Monster positions
     */
    public static List<Pos> allPos (Level l) {
        // get level array
        char[][] level = l.getLevel();
        // create a list, we will fill this list with all the Monster entities position 
        List<Pos> all  = new ArrayList<Pos>();

        // search all the Monster entities position in the level
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // if it's a Monster, put its position in all to fill the list
                if (level[i][j] == MONSTER) {
                    Pos p = new Pos(i, j);
                    all.add(p);
                }
            }
        }

        return all; // return the list
    }

    /**
     * This method returns all the Monster positions.
     * 
     * @return a list of all the Monster positions
     */
    public List<Pos> getAllPos() {
        return allEntities;
    }

    /**
     * This method as 80% of chance to moves the Monster in the level. (80% for each monster to move)
     * 
     * @param level the level array
     * @param playerPos the player position
     * @return the new level array
     */
    public char[][] randomMonsterMove(char[][] level, Pos playerPos) {
        // if there is no Monster, return the level
        if (allEntities.size() == 0) return level;
        for (Pos monsterPos : allEntities) {
            // create a Random object
            Random rand = new Random();

            // generate a random number between 0 and 100
            int rand_int = rand.nextInt(101);

            // if the random number is greater than 20, move the Monster
            if (rand_int >= 20) {
                // create a new AStarAlgo object
                AStarAlgo algo = new AStarAlgo(monsterPos, playerPos, level);
                // search the key to do
                char keyToDoResult = algo.search();

                // switch the key to do
                switch (keyToDoResult) {
                    case UP:
                        // moves the monster UP
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX() - 1);
                        monsterPos.setY(monsterPos.getY());
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case DOWN:
                        // moves the monster DOWN
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX() + 1);
                        monsterPos.setY(monsterPos.getY());
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case LEFT:
                        // moves the monster LEFT
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX());
                        monsterPos.setY(monsterPos.getY() - 1);
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case RIGHT:
                        // moves the monster RIGHT
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX());
                        monsterPos.setY(monsterPos.getY() + 1);
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case KILLED:
                        // overwrite the player with the monster
                        Pos player = Player.whereIsPlayer(level);
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(player.getX());
                        monsterPos.setY(player.getY());
                        level[player.getX()][player.getY()] = MONSTER;

                        break;

                }
            }
        }

        return level; // return the level
    }

    /**
     * This method checks if the player is on a Monster.
     * 
     * @param playerPos the player position
     * @return true if the player is on a Monster, false otherwise
     */
    public Boolean onMonster(Pos playerPos) {
        // create an index, needed to remove the Monster
        int index = 0;
        // check if the player is on a Monster
        for (Pos Entity : allEntities) {
            // if the player is on a Monster, remove the Monster and return true
            if (Entity.getX() == playerPos.getX() && Entity.getY() == playerPos.getY()) {
                allEntities.remove(index);
                return true;
            }

            index++; // increment the index
        }

        return false; // if the player is not on a Monster, return false
    }
}
