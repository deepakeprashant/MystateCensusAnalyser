package bl.csvfile.example;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    List<CSVStateCensus> stateCensusesList = new ArrayList<>();

    CSVStateCensus stateCensus = null;

    String filepath = "C:\\Users\\Admin\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\StateCensusData.csv";

    public static int openCsvBuilder(String csvFilePath, Object myClass) throws CensusAnalyserException {
        int counter = 0;
        try {
            Iterator<Object> myIterator = getIterator(csvFilePath, myClass);
            while ( myIterator.hasNext() ) {
                counter++;
                Object myObj = myIterator.next();
            }
        } catch (CensusAnalyserException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.DELIMITER_ISSUE,
                    "might be some error related to delimiter at record no. : " + (counter + 1));
        }
        return counter;
    }

    public static Iterator<Object> getIterator(String csvFilePath, Object myClass) throws CensusAnalyserException {
        Reader reader = null;
        CsvToBean<Object> csvToBean;
        try {
            reader = Files.newBufferedReader(Paths.get(csvFilePath));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType((Class) myClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<Object> myIterator = csvToBean.iterator();
            return myIterator;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.NO_SUCH_FILE,
                    "no such file exists. Please enter correct file");
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.INCORRECT_DATA_ISSUE,
                    "delimiter error at line 1 OR might be some error " +
                            "related to headers or incorrect extension / input file ");
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.SOME_OTHER_IO_EXCEPTION,
                    "Some other IO related exception");
        }
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
            while (it.hasNext()) {
                CSVStateCensus stateCensus1 = it.next();
                records.add(new String[]{
                        stateCensus1.getStateName(),
                        stateCensus1.getPopulation(),
                        stateCensus1.getAreaInSqKm()
                });
            }
            writer.writeAll(records);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
