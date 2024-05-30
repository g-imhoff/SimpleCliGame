package game.level.chooseLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelReader extends LevelChooser {
    private String levelPath;
    private int rows, columns;

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

    public char[][] readLevel() {
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
