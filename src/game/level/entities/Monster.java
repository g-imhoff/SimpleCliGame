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
        if (allEntities.size() == 0) return level;
        for (Pos monsterPos : allEntities) {
            Random rand = new Random();

            int rand_int = rand.nextInt(101);

            if (rand_int > 20) {
                AStarAlgo algo = new AStarAlgo(monsterPos, playerPos, level);
                char keyToDoResult = algo.search();

                Pos newPos;

                switch (keyToDoResult) {
                    case UP:
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX() - 1);
                        monsterPos.setY(monsterPos.getY());
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case DOWN:
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX() + 1);
                        monsterPos.setY(monsterPos.getY());
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case LEFT:
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX());
                        monsterPos.setY(monsterPos.getY() - 1);
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case RIGHT:
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(monsterPos.getX());
                        monsterPos.setY(monsterPos.getY() + 1);
                        level[monsterPos.getX()][monsterPos.getY()] = MONSTER;

                        break;

                    case KILLED:
                        Pos player = Player.whereIsPlayer(level);
                        level[monsterPos.getX()][monsterPos.getY()] = ' ';
                        monsterPos.setX(player.getX());
                        monsterPos.setY(player.getY());
                        level[player.getX()][player.getY()] = MONSTER;

                        break;

                }
            }
        }

        return level;
    }

    public Boolean onMonster(Pos playerPos) {
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
}
