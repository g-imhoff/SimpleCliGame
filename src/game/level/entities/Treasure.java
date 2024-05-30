package game.level.entities;

import game.level.Level;
import game.tools.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static game.Game.TREASURE;

public class Treasure implements Entities {
    List<Pos> allEntities = new ArrayList<Pos>();

    public Treasure(char[][] level) {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == TREASURE) {
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
                if (level[i][j] == TREASURE) {
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

    public char[][] randomNewTreasure(char[][] level) {
        Random rand = new Random();

        int rand_int = rand.nextInt(101);

        if (rand_int > 90) {
            int emptySpace = howManyEmpty(level);
            int rand_int2 = rand.nextInt(emptySpace - 1) + 1; // le rand envoie un nombre entre 0 et 22 (dans le cas ou empty space 23, +1 on a 1 a 23
            Pos posNewTreasure = spaceToPos(rand_int2, level);

            if (posNewTreasure != null) {
                level = addTreasure(posNewTreasure, level);
                allEntities.add(posNewTreasure);
            }
        }

        return level;
    }

    public int howManyEmpty(char[][] level) {
        int emptySpace = 0;
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ') emptySpace++;
            }
        }

        return emptySpace;
    }

    public Pos spaceToPos(int numberSpace, char[][] level) {
        int count = 0;

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ') count++;

                if(count == numberSpace) {
                    Pos p = new Pos(i, j);

                    return p;
                }
            }
        }

        return null;
    }

    public char[][] addTreasure(Pos p, char[][] level) {
        level[p.getX()][p.getY()] = 'T';
        return level;
    }

    public Boolean onTreasure(Pos playerPos) {
        int index = 0;
        for (Pos Entity : allEntities) {
            if (Entity.getX() == playerPos.getX() && Entity.getY() == playerPos.getY()) {
                allEntities.remove(index);
                return true;
            }

            index++;
        }

        return false;
    }

    public char[][] printBack(char[][] level) {
        for (Pos Entity : allEntities) {
            if (level[Entity.getX()][Entity.getY()] == ' ') {
                level[Entity.getX()][Entity.getY()] = 'T';
            }
        }

        return level;
    }
}
