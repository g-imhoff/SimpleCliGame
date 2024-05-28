package game.level.chooseLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelReader extends LevelChooser {
    private String levelPath;
    private int rows, columns;

    public LevelReader(String levelPath) {
        this.levelPath = levelPath;

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
        char[][] level = new char[rows][columns];

        try (BufferedReader reader = new BufferedReader(new FileReader(levelPath))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    level[row][col] = line.charAt(col);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return level;
    }
}
