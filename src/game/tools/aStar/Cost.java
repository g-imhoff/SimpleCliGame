package game.tools.aStar;

public class Cost {
    private int gCost, fCost, hCost;

    public Cost(int gCost, int fCost, int hCost) {
        this.gCost = gCost;
        this.fCost = fCost;
        this.hCost = hCost;
    }

    public int getGCost() {
        return gCost;
    }

    public int getFCost() {
        return fCost;
    }

    public int getHCost() {
        return hCost;
    }

    public String toString() {
        return "(h:" + hCost + ")";
    }
}
