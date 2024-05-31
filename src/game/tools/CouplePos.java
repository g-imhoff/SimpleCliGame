package game.tools;

/**
 * CouplePos
 * <p>
 * This class is used to manage a couple of positions, in our case a position and its parent position.
 */
public class CouplePos {
    /**
     * pos, the position
     * parentPos, the parent position
     */
    private Pos pos;
    private Pos parentPos;

    /**
     * Constructor
     *
     * @param pos       the position
     * @param parentPos the parent position
     */
    public CouplePos(Pos pos, Pos parentPos) {
        this.pos = pos;
        this.parentPos = parentPos;
    }

    /**
     * This method returns the position.
     *
     * @return the position
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * This method returns the parent position.
     *
     * @return the parent position
     */
    public Pos getParentPos() {
        return parentPos;
    }
}
