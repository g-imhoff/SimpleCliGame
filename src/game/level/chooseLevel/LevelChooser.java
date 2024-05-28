package game.level.chooseLevel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import static game.Game.MAX_LEVELS;

public class LevelChooser {
    private String levelPath = "./level"; // where is the folder containing all the level files
    private String levelChoose = ""; // what level we want to play
    private String[] levelNames = new String[MAX_LEVELS]; // all the level in levelPath
    private String finalLevelPath = ""; // the path to the level we want to play

    public LevelChooser() {}

    public void choose(Scanner scanUserInput) {
        //print all the available level
        int result = getAllLevel();

        // if printAllLevel worked fine, we keep the process of choosing
        // if printAllLevel failed, we change the path of the level folder and restart choose
        if (result == 1) {
            // print all the levels
            for (int i = 0; levelNames[i] != null; i++) {
                // print all the names files set in levelNames
                System.out.println(i + ": " + levelNames[i]);
            }

            // let the user choose between levels
            System.out.print("Choose between one of those levels : ");
            int resultLevel = scanUserInput.nextInt();

            //if the levels chose don't exist, let the user choose another time
            while(levelNames[resultLevel] == null || levelNames[resultLevel].isEmpty()) {
                System.out.println("this level don't exist, choose another one");
                resultLevel = scanUserInput.nextInt();
            }

            //set the levelChoose instance
            levelChoose = levelNames[resultLevel];


            // create the level path and set it
            finalLevelPath = levelPath + File.separator + levelChoose;
        } else {
            // let the user change the level folder
            System.out.println("The folder is empty or does not exist.");
            levelPathModifier(scanUserInput);
        }
    }

    public int getAllLevel() {
        //open the level folder
        File dossier = new File(levelPath);

        //filter to be sure to not count files that are not .txt files
        FilenameFilter filtre = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };

        // get all the files
        File[] fichiers = dossier.listFiles(filtre);

        // if the folder isnt empty, put all the files in levelNames
        if (fichiers != null && fichiers.length != 0) {
            for (int i = 0; i < fichiers.length; i++) {
                levelNames[i] = fichiers[i].getName();
            }

            return 1;
        }

        return 0;
    }

    public void levelPathModifier(Scanner scanUserInput) {
        // let the user change the path of the level folder
        System.out.println("Where is your level folder ?");
        levelPath = scanUserInput.next();
        System.out.println("Your level folder is now set at : " + levelPath);
        choose(scanUserInput);
    }

    public String getFinalLevelPath() {
        // just return the finalLevelPath
        return finalLevelPath;
    }
}
