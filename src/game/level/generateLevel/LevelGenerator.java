package game.level.generateLevel;

import game.level.Level;
import game.tools.Pos;

import static game.Game.*;

public class LevelGenerator {
    private int cols, rows;

    public LevelGenerator(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public char[][] generateLevel() {
        char[][] level = new char[cols][rows];

        level = createLimits(level);
        level = addEntity(level, PLAYER);
        level = addEntity(level, MONSTER);
        level = addEntity(level, TREASURE);
        level = addEntity(level, EXIT);

        System.out.println(Level.howManyEmpty(level));

        return level;
    }

    private char[][] createLimits(char[][] level) {
        System.out.println(cols + " " + rows);
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                if (x == 0 || y == 0 || x == cols - 1 || y == rows - 1) {
                    level[x][y] = WALL;
                } else {
                    level[x][y] = ' ';
                }
            }
        }

        return level;
    }

    private char[][] addEntity(char[][] level, char entityType) {
        int nbrEmptySpace = Level.howManyEmpty(level);
        Pos playerPos = Level.spaceToPos(nbrEmptySpace, level);

        if (playerPos != null) {
            level[playerPos.getX()][playerPos.getY()] = entityType;
        } else {
            System.out.println("Error, couldn't add a player");
        }

        return level;
    }
}
