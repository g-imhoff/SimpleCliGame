package game.level.chooseLevel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import static game.Game.MAX_LEVELS;

/**
 * LevelChooser
 * 
 * This class is used to choose a level in the level folder.
 * It will print all the levels in the folder and let the user choose one.
 * If the folder is empty, it will ask the user to change the path of the level folder.
 * 
 * The level folder is set by default at "./level".
 * 
 */

public class LevelChooser {
    private static String levelPath = "./level"; // the path to the level folder
    private String levelChoose = ""; // the level we want to play
    private String[] levelNames = new String[MAX_LEVELS]; // all the level names
    private String finalLevelPath = ""; // the final path to the level we want to play

    /**
     * Constructor
     */
    public LevelChooser() {}
    
    /**
     * This method allows the user to choose a level from the available levels.
     * It first prints all available levels. If the list of levels is empty or cannot be retrieved,
     * it prompts the user to change the level folder path and restarts the level selection process.
     * 
     * @param scanUserInput the Scanner object used to read user input from the console.
     */

    public void choose(Scanner scanUserInput) {
        // get all the levels
        int result = getAllLevel();

        // if we found some levels
        if (result == 1) {
            // print all the levels
            for (int i = 0; levelNames[i] != null; i++) {
                // print all the names files set in levelNames
                System.out.println(i + ": " + levelNames[i]);
            }

            // let the user choose between levels
            System.out.print("Choose between one of those levels : ");
            int resultLevel = scanUserInput.nextInt();
            scanUserInput.nextLine(); // to be sure that there is nothing less for future user input

            //if the levels chose don't exist, let the user choose another time
            while(levelNames[resultLevel] == null || levelNames[resultLevel].isEmpty()) {
                System.out.println("this level don't exist, choose another one");
                resultLevel = scanUserInput.nextInt();
                scanUserInput.nextLine();
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

    /**
     * This method retrieves all the levels in the level folder.
     * It filters the files in the folder to only include .txt files.
     * 
     * @return 1 if levels are found, 0 otherwise.
     */

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

            return 1; //this return tell us that we found some level
        }

        return 0; //this tell us that the folder where we searched level contains absolutly no level
    }

    /**
     * This method allows the user to change the path of the level folder.
     * 
     * @param scanUserInput the Scanner object used to read user input from the console.
     */

    public void levelPathModifier(Scanner scanUserInput) {
        System.out.println("Where is your level folder ?");
        levelPath = scanUserInput.next();
        System.out.println("Your level folder is now set at : " + levelPath);
        choose(scanUserInput);
    }

    /**
     * This method returns the final path to the level we want to play.
     * 
     * @return the final path to the level we want to play.
     */

    public String getFinalLevelPath() {
        return finalLevelPath;
    }
}
