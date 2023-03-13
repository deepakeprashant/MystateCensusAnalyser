package bl.census;
import bl.csvfile.example.*;
import bl.csvfile.example.CSVStateCensus;
import bl.csvfile.example.CensusAnalyserException;
import bl.csvfile.example.StateCensusAnalyser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    public static final String STATECODES_CSVFILE = "/home/admin97/Ayush/github/MyCensusAnalyser/src/main/resources/StateCode.csv";
    public static final String STATECENSUS_CSVFILE = "C:\\Users\\Admin\\IdeaProjects\\StateCensusAnalyser\\src\\main\\resources\\StateCensusData.csv";
    public static final String WRONG_FILE = "/useless.txt";

    @Test
    public void GivenTheStateCodesCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(STATECENSUS_CSVFILE, CSVStateCensus.class);
            System.out.println(count);
            Assertions.assertEquals(4,count);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
