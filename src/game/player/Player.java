package game.player;

import game.level.entities.Door;
import game.tools.Pos;
import game.level.Level;

public class Player {
    Pos p;

    public Player(char[][] level) {
        this.p = whereIsPlayer(level);
    }

    public Pos getPos() {
        return p;
    }

    public void setPos(Pos p) {
        this.p = p;
    }

    public static Pos whereIsPlayer(char[][] level) {
        Pos p = null;
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == 'J') {
                    p = new Pos(i, j);
                }
            }
        }

        return p;
    }

    public char[][] move(Pos move, char[][] level) {
        Pos newPos = Pos.add(p, move);
        System.out.println(newPos);
        if (posAvailable(newPos, level)) {
            level[newPos.getX()][newPos.getY()] = 'J';
            level[p.getX()][p.getY()] = ' ';
            p = newPos;
        }

        return level;
    }

    public Boolean posAvailable(Pos p, char[][] level) {
        return level[p.getX()][p.getY()] != '#';
    }

    public Boolean onExit() {
        return false;
    }
}
