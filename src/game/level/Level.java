package game.level;

import game.level.chooseLevel.LevelChooser;

public class Level {
    private char[] level;

    public void chooseLevel() {
        LevelChooser levelChooser = new LevelChooser();
        levelChooser.choose();
        
    }

    public void generateLevel() {

    }

    public char[] getLevel() {
        return level;
    }
}
