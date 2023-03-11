package bl.csvfile.example;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

    @CsvBindByName(column = "State")
    private String stateName;

    @CsvBindByName(column = "Population", required = true)
    private String population;

    @CsvBindByName(column = "AreaInSqKm")
    private String areaInSqKm;

    public CSVStateCensus() {
    }

    public CSVStateCensus(String stateName, String population, String areaInSqKm) {
        this.stateName = stateName;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
    }

    @Override
    public String toString() {
        return "CSVStateCensus{" +
                "stateName='" + stateName + '\'' +
                ", population='" + population + '\'' +
                ", areaInSqKm='" + areaInSqKm + '\'' +
                '}';
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }
}