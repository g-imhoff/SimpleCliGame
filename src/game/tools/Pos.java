package game.tools;

public class Pos {
    private int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static Pos add(Pos p1, Pos p2) {
        return new Pos(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }
}
