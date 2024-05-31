package game.tools.aStar;

import game.tools.CouplePos;
import game.tools.Pos;

import java.util.ArrayList;

import static game.Game.*;

/**
 * fastestPathSearcher
 * <p>
 * This class is used to find the fastest path between two positions.
 */
public class fastestPathSearcher {
    /**
     * levelCost, the cost (f, g, h) of all the positions
     * startPos, the start position
     * destPos, the destination position
     * currentPos, the current position
     * posOpened, the opened positions
     * posChecked, the checked positions
     * parentPos, the parent positions
     * destReached, true if the destination is reached, false otherwise
     */

    private Cost[][] levelCost;

    private Pos startPos, destPos, currentPos;
    private ArrayList<Pos> posOpened = new ArrayList<>();
    private ArrayList<Pos> posChecked = new ArrayList<>();
    private ArrayList<CouplePos> parentPos = new ArrayList<CouplePos>();

    private boolean destReached = false;

    /**
     * Constructor
     *
     * @param levelCost the cost (f, g, h) of all the positions
     */
    public fastestPathSearcher(Cost[][] levelCost) {
        this.levelCost = levelCost;
    }

    /**
     * This method searches the fastest path between two positions.
     *
     * @param startPos the start position
     * @param destPos  the destination position
     * @return the direction to go
     */
    public char search(Pos startPos, Pos destPos) {
        // initialize the variables of position
        this.startPos = startPos;
        currentPos = startPos;
        this.destPos = destPos;

        // search until the destination is reached
        while (destReached == false) {
            // get the position of the current position
            int x = currentPos.getX();
            int y = currentPos.getY();

            // add the current position to the checked positions
            posChecked.add(currentPos);
            // remove the current position from the opened positions
            posOpened.remove(currentPos);

            // check the positions around the current position
            if (levelCost[x][y - 1] != null) {
                Pos left = new Pos(x, y - 1);
                openPos(left);
            }

            if (levelCost[x][y + 1] != null) {
                Pos right = new Pos(x, y + 1);
                openPos(right);
            }

            if (levelCost[x - 1][y] != null) {
                Pos up = new Pos(x - 1, y);
                openPos(up);
            }

            if (levelCost[x + 1][y] != null) {
                Pos down = new Pos(x + 1, y);
                openPos(down);
            }

            // get the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = Integer.MAX_VALUE;

            // check between all the opened positions wich one as the best cost
            for (int i = 0; i < posOpened.size(); i++) {
                Pos pos = posOpened.get(i);
                Cost cost = levelCost[pos.getX()][pos.getY()];

                // if the f cost is lower than the best f cost, set the new best f cost and index
                if (cost.getFCost() < bestNodeFCost) {
                    bestNodeFCost = cost.getFCost();
                    bestNodeIndex = i;
                }

                // if the f cost is equal to the best f cost, check the g cost
                if (cost.getFCost() == bestNodeFCost) {
                    Pos bestActualCost = posOpened.get(bestNodeIndex);

                    // if the g cost is lower than the best g cost, set the new best f cost and index
                    if (cost.getGCost() < levelCost[bestActualCost.getX()][bestActualCost.getY()].getGCost()) {
                        bestNodeIndex = i;
                        bestNodeFCost = cost.getFCost();
                    }
                }
            }

            // set the current position to the best node
            currentPos = posOpened.get(bestNodeIndex);

            // check if the destination is the currentPos to stop the search
            if (currentPos.equals(destPos)) {
                destReached = true;
            }
        }

        // when the destination is reached, get the path
        ArrayList<Pos> path = getPath();

        // if the path is empty, that means that there is no space between the start and the destination, we are from one move of the destination
        // that means that the next move will, in our context, kill the player
        if (path.size() == 0) {
            return KILLED;
        }

        // if the path is not empty, return the direction to go
        return posToMove(startPos, path.get(path.size() - 1));
    }

    /**
     * This method returns the path between the start and the destination.
     *
     * @return the path between the start and the destination
     */
    public ArrayList<Pos> getPath() {
        // set the current position to the destination position
        Pos current = destPos;
        // create the result array
        ArrayList<Pos> result = new ArrayList<>();

        // add the destination position to the result array until you reach the startPos
        while (!current.equals(startPos)) {
            // get the parent position
            Pos parent = findParentPos(current);
            // set the current position to the parent position
            current = parent;

            // if the parent position is not null and not the start position, add it to the result array
            if (parent != null) {
                if (!parent.equals(startPos)) {
                    result.add(parent);
                }
            } else {
                return null; // if the parent position is null, return null
            }
        }

        return result; // return the result array
    }

    /**
     * This method returns the parent position of a position.
     *
     * @param pos the position
     * @return the parent position
     */
    public Pos findParentPos(Pos pos) {
        // search in the parentPos array the parent position of the position
        for (CouplePos c : parentPos) {
            if (c.getPos().equals(pos)) return c.getParentPos();
        }

        return null; // return null if the pos has no parent
    }

    /**
     * This method opens a position.
     *
     * @param p the position to open
     */
    public void openPos(Pos p) {
        // check if the position is not already opened or checked
        Boolean checkCondition = true;

        for (Pos openedPos : posOpened) {
            if (p.equals(openedPos)) checkCondition = false;
        }

        for (Pos checkedPos : posChecked) {
            if (p.equals(checkedPos)) checkCondition = false;
        }

        // if the position is not already opened or checked, add it to the opened positions and add the parent position
        if (checkCondition) {
            parentPos.add(new CouplePos(p, currentPos));
            posOpened.add(p);
        }
    }

    /**
     * This method returns the direction to go between two positions, it basically give if you need to go UP, DOWN, LEFT or RIGHT from two positions.
     *
     * @param myPos the position of the entity
     * @param toGo  the position to go
     * @return the direction to go
     */
    public char posToMove(Pos myPos, Pos toGo) {
        // get the x distance between the two positions
        int xDest = myPos.getX() - toGo.getX();

        // if the x distance is not 0, return the direction to go
        if (xDest != 0) {
            return xDest == -1 ? DOWN : UP; // if the x distance is -1, go down, else go up
        } else {
            // if the x distance is 0, get the y distance between the two positions
            int yDest = myPos.getY() - toGo.getY();

            // if the y distance is not 0, return the direction to go
            if (yDest != 0) {
                return yDest == -1 ? RIGHT : LEFT; // if the y distance is -1, go right, else go left
            } else {
                return KILLED; // if the x and y distance are 0, return KILLED, because it means there is no move to do
            }
        }
    }
}
