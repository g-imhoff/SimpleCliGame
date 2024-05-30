package game.tools.aStar;

import game.tools.Pos;

import static game.Game.UP;
import static game.Game.DOWN;
import static game.Game.LEFT;
import static game.Game.RIGHT;

public class fastestPathSearcher {
    private Cost[][] levelCost;
    private char[][] level;

    public fastestPathSearcher(Cost[][] levelCost, char[][] level) {
        this.levelCost = levelCost;
        this.level = level;
    }

    public char search(Pos startPos, Pos goalPos) {
        return UP;
    }
}
