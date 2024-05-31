package game.tools.aStar;

import game.tools.Pos;
import game.tools.CouplePos;

import java.util.ArrayList;

import static game.Game.*;

public class fastestPathSearcher {
    private Cost[][] levelCost;
    private char[][] level;

    private Pos startPos, destPos, currentPos;
    private ArrayList<Pos> posOpened = new ArrayList<>();
    private ArrayList<Pos> posChecked = new ArrayList<>();
    private ArrayList<CouplePos> parentPos = new ArrayList<CouplePos>();

    private boolean destReached = false;

    public fastestPathSearcher(Cost[][] levelCost, char[][] level) {
        this.levelCost = levelCost;
        this.level = level;
    }

    public char search(Pos startPos, Pos destPos) {
        this.startPos = startPos;
        currentPos = startPos;
        this.destPos = destPos;

        while (destReached == false) {
            int x = currentPos.getX();
            int y = currentPos.getY();

            posChecked.add(currentPos);
            posOpened.remove(currentPos);

            if (levelCost[x][y - 1] != null) {
                Pos left = new Pos(x, y - 1);
                openPos(left);
            }

            if (levelCost[x][y + 1] != null) {
                Pos right = new Pos(x, y + 1);
                openPos(right);
            }

            if (levelCost[x - 1][y] != null) {
                Pos up = new Pos( x - 1, y);
                openPos(up);
            }

            if (levelCost[x + 1][y] != null) {
                Pos down = new Pos(x + 1, y);
                openPos(down);
            }

            int bestNodeIndex = 0;
            int bestNodeFCost = Integer.MAX_VALUE;

            for (int i = 0; i < posOpened.size(); i++) {
                Pos pos = posOpened.get(i);
                Cost cost = levelCost[pos.getX()][pos.getY()];

                if (cost.getFCost() < bestNodeFCost) {
                    bestNodeFCost = cost.getFCost();
                    bestNodeIndex = i;
                }

                if (cost.getFCost() == bestNodeFCost) {
                    Pos bestActualCost = posOpened.get(bestNodeIndex);
                    if (cost.getGCost() < levelCost[bestActualCost.getX()][bestActualCost.getY()].getGCost()) {
                        bestNodeIndex = i;
                        bestNodeFCost = cost.getFCost();
                    }
                }
            }

            currentPos = posOpened.get(bestNodeIndex);

            if (currentPos.equals(destPos)) {
                destReached = true;
            }
        }

        ArrayList<Pos> path = getPath();

        if (path.size() == 0) {
            return KILLED;
        }

        return posToMove(startPos, path.get(path.size() - 1));
    }

    public ArrayList<Pos> getPath() {
        Pos current = destPos;
        ArrayList<Pos> result = new ArrayList<>();

        while (!current.equals(startPos)) {
            Pos parent = findParentPos(current);
            current = parent;

            if (parent != null) {
                if (!parent.equals(startPos)) {
                    result.add(parent);
                }
            } else {
                return null;
            }
        }

        return result;
    }

    public Pos findParentPos(Pos pos) {
        for (CouplePos c : parentPos) {
            if (c.getPos().equals(pos)) return c.getParentPos();
        }

        return null;
    }

    public void openPos(Pos p) {
        Boolean checkCondition = true;

        for (Pos openedPos : posOpened) {
            if (p.equals(openedPos)) checkCondition = false;
        }

        for (Pos checkedPos : posChecked) {
            if (p.equals(checkedPos)) checkCondition = false;
        }

        if (checkCondition) {
            parentPos.add(new CouplePos(p, currentPos));
            posOpened.add(p);
        }
    }

    public char posToMove(Pos myPos, Pos toGo) {
        int xDest = myPos.getX() - toGo.getX();

        if (xDest != 0) {
            return xDest == -1 ? DOWN : UP;
        } else {
            int yDest = myPos.getY() - toGo.getY();

            if (yDest != 0) {
                return yDest == -1 ? RIGHT : LEFT;
            } else {
                return KILLED;
            }
        }
    }
}
