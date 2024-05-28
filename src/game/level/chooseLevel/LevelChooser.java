package game.level.chooseLevel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import static game.Game.MAX_LEVELS;

public class LevelChooser {
    private String levelPath = "./level";
    private String levelChoose = "";
    private String[] levelNames = new String[MAX_LEVELS];;
    private String finalLevelPath = "";

    public LevelChooser() {}

    public void choose() {
        //print all the available level
        int result = getAllLevel();

        // if printAllLevel worked fine, we keep the process of choosing
        // if printAllLevel failed, we change the path of the level folder and restart choose
        if (result == 1) {
            for (int i = 0; levelNames[i] != null; i++) {
                // print all the names files set in levelNames
                System.out.println(i + ": " + levelNames[i]);
            }

            try (Scanner scanUserInput = new Scanner(System.in)) {
                System.out.println("Choose between one of those levels : ");
                int resultLevel = scanUserInput.nextInt();

                while(levelNames[resultLevel] == null || levelNames[resultLevel].isEmpty()) {
                    System.out.println("this level don't exist, choose another one");
                    resultLevel = scanUserInput.nextInt();
                }

                levelChoose = levelNames[resultLevel];
            }

            finalLevelPath = levelPath + File.separator + levelChoose;
        } else {
            System.out.println("The folder is empty or does not exist.");
            levelPathModifier();
            return;
        }
    }

    public int getAllLevel() {
        File dossier = new File(levelPath);

        FilenameFilter filtre = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };

        File[] fichiers = dossier.listFiles(filtre);

        if (fichiers != null && fichiers.length != 0) {
            int count = 0;
            for (File fichier : fichiers) {
                levelNames[count] = fichier.getName();
                count++;
            }

            return 1;
        }

        return 0;
    }

    public void levelPathModifier() {
        try (Scanner scanUserInput = new Scanner(System.in)) {
            System.out.println("Where is your level folder ?");
            levelPath = scanUserInput.next();
            System.out.println("Your level folder is now set at : " + levelPath);
            choose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFinalLevelPath() {
        return finalLevelPath;
    }
}
