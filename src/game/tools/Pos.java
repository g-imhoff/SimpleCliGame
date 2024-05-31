package game.tools;

/**
 * Pos
 * 
 * This class is used to manage a position.
 */
public class Pos {
    /**
     * x, the x position
     * y, the y position
     */
    private int x, y;

    /**
     * Constructor
     * 
     * @param x the x position
     * @param y the y position
     */
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the x position.
     * @return the x position
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns the y position.
     * @return the y position
     */
    public int getY() {
        return y;
    }

    /**
     * This method sets the x position.
     * @param x the new x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method sets the y position.
     * @param y the new y position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method returns the position in a string, used for print on the terminal or things like that.
     * @return the position in a string
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * This method returns the distance between two positions.
     * 
     * @param p1 the first position
     * @param p2 the second position
     * @return the distance between the two positions
     */
    public static Pos add(Pos p1, Pos p2) {
        return new Pos(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }

    /**
     * This method checks if two position as the same coordinates.
     * @param p the position to compare
     * @return true if the two positions have the same coordinates, false otherwise
     */
    public Boolean equals (Pos p) {
        return this.x == p.getX() && this.y == p.getY();
    }
}
