package game.tools.aStar;

/**
 * Cost
 * <p>
 * This class is used to manage the cost of a position.
 */
public class Cost {
    /**
     * gCost, the cost to move from the starting point to a given position
     * fCost, the total cost of a position
     * hCost, the cost to move from a given position to the goal
     */
    private int gCost, fCost, hCost;

    /**
     * Constructor
     *
     * @param gCost the cost to move from the starting point to a given position
     * @param fCost the total cost of a position
     * @param hCost the cost to move from a given position to the goal
     */
    public Cost(int gCost, int fCost, int hCost) {
        this.gCost = gCost;
        this.fCost = fCost;
        this.hCost = hCost;
    }

    /**
     * This method returns the g cost.
     *
     * @return the g cost
     */
    public int getGCost() {
        return gCost;
    }

    /**
     * This method returns the f cost.
     *
     * @return the f cost
     */
    public int getFCost() {
        return fCost;
    }

    /**
     * This method returns the h cost.
     *
     * @return the h cost
     */
    public int getHCost() {
        return hCost;
    }

    /**
     * This method returns the cost of a position in a string, used for print on the terminal or things like that.
     *
     * @return the cost of a position in a string
     */
    public String toString() {
        return "(f:" + fCost + ")";
    }
}
