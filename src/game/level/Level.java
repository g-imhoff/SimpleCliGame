package game.level;

import game.level.chooseLevel.LevelChooser;
import game.level.chooseLevel.LevelReader;

public class Level {
    private char[][] level;

    public void chooseLevel() {
        LevelChooser levelChooser = new LevelChooser();
        levelChooser.choose();
        String levelPath = levelChooser.getFinalLevelPath();
        LevelReader levelReader = new LevelReader(levelPath);
        level = levelReader.readLevel();
    }

    public void generateLevel() {

    }

    public char[][] getLevel() {
        return level;
    }

    public void printLevel() {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
    }
}
