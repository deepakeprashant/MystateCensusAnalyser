package bl.csvfile.example;

import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    List<CSVStateCensus> stateCensusesList = new ArrayList<>();

    CSVStateCensus stateCensus = null;

    String filepath = "C:\\Users\\Admin\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\StateCensusData.csv";

    public static int openCsvBuilder(String statecensusCsvfile, Class<CSVStateCensus> csvStateCensusClass) {
    }

    public void addStateCensusData(Scanner scan) {
        System.out.println("Enter State Name");
        String state = scan.next();
        System.out.println("Enter Total Population");
        String population = scan.next();
        System.out.println("Enter Area In Square km");
        String areaInSqKm = scan.next();
        stateCensus = new CSVStateCensus(state, population, areaInSqKm);
        stateCensusesList.add(stateCensus);
    }

    public void headerCSVFile() {
        String state = "STATE NAME";
        String population = "POPULATION";
        String areaInSQKm = "AREA IN SQUARE KM";
        stateCensus = new CSVStateCensus(state, population, areaInSQKm);
        stateCensusesList.add(stateCensus);
    }

    public void writeDataIntoCsvFile() throws FileNotFoundException {
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            CSVWriter writer = new CSVWriter(fileWriter);
            List<String[]> records = new ArrayList<String[]>();
            Iterator<CSVStateCensus> it = stateCensusesList.iterator();
            while (it.hasNext()){
                CSVStateCensus stateCensus1 = it.next();
                records.add(new String[]{
                   stateCensus1.getStateName(),
                   stateCensus1.getPopulation(),
                        stateCensus1.getAreaInSqKm()
                });
            }
            writer.writeAll(records);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
