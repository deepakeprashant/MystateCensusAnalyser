package bl.census;
import bl.csvfile.example.*;
import bl.csvfile.example.CSVStateCensus;
import bl.csvfile.example.CensusAnalyserException;
import bl.csvfile.example.StateCensusAnalyser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    public static final String STATECENSUS_CSVFILE = "C:\\Users\\Admin\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\StateCensusData.csv";
    public static final String WRONG_FILE = "/useless.txt";

    @Test
    public void givenTheStateCensusCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(STATECENSUS_CSVFILE, CSVStateCensus.class);
            System.out.println(count);
            Assertions.assertEquals(4,count);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusCsvFile_IfIncorrect_ShouldReturnException() throws IOException{
        try {
            int count = StateCensusAnalyser.openCsvBuilder(WRONG_FILE, CSVStateCensus.class);
        }catch (CensusAnalyserException e){
            e.printStackTrace();
            Assertions.assertEquals(CensusAnalyserException.CensusExceptionType.NO_SUCH_FILE,e.type);
        }
    }
}
