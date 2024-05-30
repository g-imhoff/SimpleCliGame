package game.level.entities;

import game.level.Level;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;

import static game.Game.DOOR;

public class Door implements Entities {
    List<Pos> allEntities = new ArrayList<Pos>(); //all the entities position is here

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

    public Boolean isit(Pos p) {
        //check if there is a position with the same
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true;
        }

        return false;
    }

    public static List<Pos> allPos (Level l) {
        char[][] level = l.getLevel();
        List<Pos> all  = new ArrayList<Pos>();
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == DOOR) {
                    Pos p = new Pos(i, j);
                    all.add(p);
                }
            }
        }

        return all;
    }

    public List<Pos> getAllPos() {
        return allEntities;
    }
}
