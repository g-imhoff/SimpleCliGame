package game.tools.aStar;

import game.tools.Pos;

import static game.Game.DOOR;
import static game.Game.WALL;


public class AStarAlgo {
    private Pos startPos;
    private Pos goalPos;
    private Cost[][] levelCost;

    public AStarAlgo(Pos startPos, Pos goalPos, char[][] level) {
        this.startPos = startPos;
        this.goalPos = goalPos;
        this.levelCost = initAllCost(level);
    }

    public Cost initCost(Pos current) {
        int xDistance = (int) (Math.pow((double) current.getX(), 2.0) - Math.pow((double) startPos.getX(), 2.0));
        int yDistance = (int) (Math.pow((double) current.getY(), 2.0) - Math.pow((double) startPos.getY(), 2.0));
        int gCost = xDistance + yDistance;

        xDistance = (int) (Math.pow((double) current.getX(), 2.0) - Math.pow((double) goalPos.getX(), 2.0));
        yDistance = (int) (Math.pow((double) current.getY(), 2.0) - Math.pow((double) goalPos.getY(), 2.0));
        int hCost = xDistance + yDistance;

        int fCost = gCost + hCost;

        return new Cost(gCost, hCost, fCost);
    }

    public Cost[][] initAllCost (char[][] level) {
        Cost[][] result = new Cost[level.length][level[0].length];

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ' || level[i][j] == DOOR) {
                    result[i][j] = initCost(new Pos(i, j));
                }
            }
        }

        return result;
    }

    public void printAllCost() {
        for (int i = 0; i < levelCost.length; i++) {
            for (int j = 0; j < levelCost[i].length; j++) {
                if (levelCost[i][j] == null) System.out.print(WALL);
                else System.out.print(levelCost[i][j]);
            }
            System.out.println();
        }
    }

    public Cost[][] getLevelCost() {
        return levelCost;
    }
}
