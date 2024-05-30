package game.level.entities;

import game.level.Level;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;

import static game.Game.EXIT;
import static game.Game.TREASURE;

public class Exit implements Entities {
    List<Pos> allEntities = new ArrayList<Pos>();

    public Exit(char[][] level) {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == EXIT) {
                    Pos p = new Pos(i, j);
                    allEntities.add(p);
                }
            }
        }
    }

    public Boolean isit(Pos p) {
        for (Pos Entity : allEntities) {
            if (Entity.equals(p)) return true;
        }

        return false;
    }

    static List<Pos> allPos (Level l) {
        char[][] level = l.getLevel();
        List<Pos> all  = new ArrayList<Pos>();
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == EXIT) {
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

    public Boolean onExit(Pos playerPos) {
        for (Pos Entity : allEntities) {
            if (Entity.getX() == playerPos.getX() && Entity.getY() == playerPos.getY()) {
                return true;
            }
        }

        return false;
    }
}
