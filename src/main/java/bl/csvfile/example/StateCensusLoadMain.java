package bl.csvfile.example;

import java.io.IOException;
import java.util.Scanner;

public class StateCensusLoadMain {
    public static final int ADD_STATE_CENSUS_DATA = 1;
    public static final int ADD_DATA_INTO_CSV_FILE = 2;
    public static final int DISPLAY_DATA = 3;
    public static final int EXIT = 4;
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
        stateCensusAnalyser.headerCSVFile();
        while (true){
            int choice = userInterface(scan);
            switch (choice){
                case ADD_STATE_CENSUS_DATA :
                    stateCensusAnalyser.addStateCensusData(scan);
                    break;
                case ADD_DATA_INTO_CSV_FILE:
                    stateCensusAnalyser.writeDataIntoCsvFile();
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

    private static int userInterface(Scanner scan) {
        System.out.println("Enter 1] Add State Census Data " +
                           "\nEnter 2] Write Data In CSV File" +
                           "\nEnter 4] Exit");
        return scan.nextInt();
    }
}
