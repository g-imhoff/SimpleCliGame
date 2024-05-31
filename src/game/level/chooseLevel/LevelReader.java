package game.level.chooseLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static game.Game.*;

/**
 * LevelReader
 * <p>
 * This class is used to read a level from a file.
 * <p>
 * The level is read from a file and put into a char[][].
 */

public class LevelReader {
    /**
     * The path to the level file
     */
    private String levelPath;

    /**
     * The number of rows and columns in the level
     */
    private int rows, columns;

    /**
     * Constructor
     *
     * @param levelPath the path to the level file
     */
    public LevelReader(String levelPath) {
        this.levelPath = levelPath; //set the levelPath

        //read the file, to set the rows and the columns
        try (BufferedReader reader = new BufferedReader(new FileReader(levelPath))) {
            String line = reader.readLine();
            if (line != null) {
                columns = line.length();
            }

            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            rows = count + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the level from the file and put it into a char[][] array.
     *
     * @return the level in a char[][] array
     */

    public char[][] readLevel() {
        // if the rows or the columns are not in the right range, return null
        if (rows <= MIN_ROWS_SIZE || rows >= MAX_ROWS_SIZE || columns <= MIN_COLS_SIZE || columns >= MAX_COLS_SIZE) {
            return null;
        }
        char[][] level = new char[rows][columns]; // create a char[][] with the right size

        //read the file (location : levelPath)
        try (BufferedReader reader = new BufferedReader(new FileReader(levelPath))) {
            String line;
            int row = 0;

            // read each line in the file
            while ((line = reader.readLine()) != null) {
                // handle the line with one char at the time
                for (int col = 0; col < line.length(); col++) {
                    level[row][col] = line.charAt(col); // put the char into the char[][] array
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace(); // if there is an error, print the error
        }

        return level; // return the level
    }
}
