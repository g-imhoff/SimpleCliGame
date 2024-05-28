package game.player;

import game.tools.Pos;
import game.level.Level;

public class Player {
    Pos p;

    public Player() {}

    public Pos getPos() {
        return p;
    }

    public void setPos(Pos p) {
        this.p = p;
    }

    public Pos whereIsPlayer(char[][] level) {
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

    public void move(char moveKey) {

    }

    public Boolean onExit() {
        return false;
    }
}
