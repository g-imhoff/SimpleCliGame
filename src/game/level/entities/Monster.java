package game.level.entities;

import game.level.Level;
import game.tools.Pos;
import game.tools.aStar.AStarAlgo;

import java.util.ArrayList;
import java.util.List;

import static game.Game.MONSTER;

public class Monster implements Entities {
    List<Pos> allEntities = new ArrayList<Pos>();

    public Monster(char[][] level) {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == MONSTER) {
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

    public static List<Pos> allPos (Level l) {
        char[][] level = l.getLevel();
        List<Pos> all  = new ArrayList<Pos>();
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == MONSTER) {
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

    public char[][] randomMonsterMove(char[][] level, Pos playerPos) {
        for (Pos monsterPos : allEntities) {
            AStarAlgo algo = new AStarAlgo(monsterPos, playerPos, level);
            algo.printAllCost();
        }

        return level;
    }


}
