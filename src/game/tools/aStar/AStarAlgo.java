package game.tools.aStar;

import game.tools.Pos;

import static game.Game.*;

/**
 * AStarAlgo
 * <p>
 * This class is used to manage the A* algorithm, this class will only search all the costs (f, g, h) of all the positions and use
 * the fastestPathSearcher class to find the shortest path between two points.
 * <p>
 * The A* algorithm is used to find the shortest path between two points.
 */

public class AStarAlgo {
    /**
     * startPos, the start position
     * goalPos, the goal position
     * levelCost, the cost (f, g, h) of all the positions
     * searcher, the fastest path searcher (used to find the shortest path between two points)
     */
    private Pos startPos;
    private Pos goalPos;
    private Cost[][] levelCost;
    private fastestPathSearcher searcher;

    /**
     * Constructor
     *
     * @param startPos the start position
     * @param goalPos  the goal position
     * @param level    the level array
     */
    public AStarAlgo(Pos startPos, Pos goalPos, char[][] level) {
        this.startPos = startPos;
        this.goalPos = goalPos;
        this.levelCost = initAllCost(level);
        this.searcher = new fastestPathSearcher(levelCost);
    }

    /**
     * This method initializes the cost of a position.
     *
     * @param current the position to initialize
     * @return the cost of the position
     */
    public Cost initCost(Pos current) {
        // create the g cost
        int xDistance = Math.abs(current.getX() - startPos.getX());
        int yDistance = Math.abs(current.getY() - startPos.getY());
        int gCost = xDistance + yDistance;

        // create the h cost
        xDistance = Math.abs(current.getX() - goalPos.getX());
        yDistance = Math.abs(current.getX() - goalPos.getX());
        int hCost = xDistance + yDistance;

        // create the f cost
        int fCost = gCost + hCost;

        // return the cost
        return new Cost(gCost, hCost, fCost);
    }

    /**
     * This method initializes the cost of all the positions.
     *
     * @param level the level array
     * @return the cost of all the positions
     */
    public Cost[][] initAllCost(char[][] level) {
        // create the result array with the right dimensions
        Cost[][] result = new Cost[level.length][level[0].length];

        // search all the positions
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                // calculate the cost only in certains condition (for exemple : no cense to calcul it for wall, it's impossible to go through it)
                if (level[i][j] == ' ' || level[i][j] == DOOR || level[i][j] == TREASURE || level[i][j] == PLAYER || level[i][j] == MONSTER) {
                    result[i][j] = initCost(new Pos(i, j)); // initialize the cost
                }
            }
        }

        return result; // return the Cost[][] array
    }

    /**
     * This method prints all the costs.
     * <p>
     * only use for debug purpose
     */
    public void printAllCost() {
        // go through all the costs
        for (int i = 0; i < levelCost.length; i++) {
            for (int j = 0; j < levelCost[i].length; j++) {
                // if the cost is == null, print a wall (it means that there was no reason to calculate the cost of this position)
                if (levelCost[i][j] == null) System.out.print("  " + WALL + "  ");
                    // else print the cost
                else System.out.print(levelCost[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method returns the level cost.
     *
     * @return the level cost
     */
    public Cost[][] getLevelCost() {
        return levelCost; // return the level cost
    }

    /**
     * This method returns the start position.
     *
     * @return the start position
     */
    public char search() {
        return searcher.search(startPos, goalPos); // return the result of the search
    }
}
