package game.tools.aStar;

import game.tools.Pos;

import java.util.Random;

import static game.Game.*;


public class AStarAlgo {
    private Pos startPos;
    private Pos goalPos;
    private Cost[][] levelCost;
    private fastestPathSearcher searcher;

    public AStarAlgo(Pos startPos, Pos goalPos, char[][] level) {
        this.startPos = startPos;
        this.goalPos = goalPos;
        this.levelCost = initAllCost(level);
        this.searcher = new fastestPathSearcher(levelCost, level);
    }

    public Cost initCost(Pos current) {
        int xDistance = Math.abs(current.getX() - startPos.getX());
        int yDistance = Math.abs(current.getY()- startPos.getY());
        int gCost = xDistance + yDistance;

        xDistance = Math.abs(current.getX() - goalPos.getX());
        yDistance = Math.abs(current.getX() - goalPos.getX());
        int hCost = xDistance + yDistance;

        int fCost = gCost + hCost;

        return new Cost(gCost, hCost, fCost);
    }

    public Cost[][] initAllCost (char[][] level) {
        Cost[][] result = new Cost[level.length][level[0].length];

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ' || level[i][j] == DOOR || level[i][j] == TREASURE) {
                    result[i][j] = initCost(new Pos(i, j));
                }
            }
        }

        return result;
    }

    public void printAllCost() {
        for (int i = 0; i < levelCost.length; i++) {
            for (int j = 0; j < levelCost[i].length; j++) {
                if (levelCost[i][j] == null) System.out.print("  " + WALL + "  ");
                else System.out.print(levelCost[i][j]);
            }
            System.out.println();
        }
    }

    public Cost[][] getLevelCost() {
        return levelCost;
    }

    public char search () {
        return searcher.search(startPos, goalPos);
    }
}
