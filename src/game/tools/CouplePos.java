package game.tools;

public class CouplePos {
    private Pos pos;
    private Pos parentPos;

    public CouplePos(Pos pos, Pos parentPos) {
        this.pos = pos;
        this.parentPos = parentPos;
    }

    public Pos getPos() {
        return pos;
    }

    public Pos getParentPos() {
        return parentPos;
    }
}
