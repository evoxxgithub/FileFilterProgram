package app;

import app.filtering.FileFilter;
import app.filtering.FileFilterBuilder;
import app.filtering.FilterStrategies;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        boolean keepRunning = true;
        while(keepRunning) {
            keepRunning = runAppLoop();
        }
    }

    private static boolean runAppLoop() {
        boolean keepRunning;Scanner sc = new Scanner(System.in);
        String inputPath = null;
        String comparePath = null;
        boolean valid = false;
        while(!valid) {
            System.out.println("type the name of the file containing data you want to be filtered");
            inputPath = sc.nextLine();
            System.out.println("type the name of the file containing data you want to use to filter");
            comparePath = sc.nextLine();
            System.out.println("");
            valid = (inputPath != null && comparePath != null);
        }

        filter(inputPath, comparePath);
        keepRunning = !sc.nextLine().equals("exit");
        return keepRunning;
    }

    private static void filter(String inputPath, String comparePath) {
        FileFilter filter = new FileFilterBuilder()
                .setFilterStrategy(FilterStrategies.FOLDER_IN_LINK)
                .setToFilterSource(inputPath)
                .setToCompareSource(comparePath)
                .build();
        filter.filter();
        Logger.getLogger("LINKFISCHER").log(Level.INFO, "Your Files have been filtered!");
        System.out.println("Type 'exit' to exit the program");
    }
}
