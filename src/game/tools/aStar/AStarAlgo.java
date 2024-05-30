package game.tools.aStar;

import game.tools.Pos;

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
        int xDistance = current.getX() - startPos.getX();
        int yDistance = current.getY() - startPos.getY();
        int gCost = xDistance + yDistance;

        xDistance = current.getX() - goalPos.getX();
        yDistance = current.getY() - goalPos.getY();
        int hCost = xDistance + yDistance;

        int fCost = gCost + hCost;

        return new Cost(gCost, hCost, fCost);
    }

    public Cost[][] initAllCost (char[][] level) {
        Cost[][] result = new Cost[level.length][level[0].length];

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == ' ') {
                    result[i][j] = initCost(new Pos(i, j));
                }
            }
        }

        return result;
    }

    public void printAllCost() {
        for (int i = 0; i < levelCost.length; i++) {
            for (int j = 0; j < levelCost[i].length; j++) {
                System.out.print(levelCost[i][j] + " ");
            }
            System.out.println();
        }
    }
}
