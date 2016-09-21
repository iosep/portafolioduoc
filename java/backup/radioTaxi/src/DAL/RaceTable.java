package DAL;

/**
 *
 * @author JoseOnate
 */
public class RaceTable {

    private int raceAmount;
    private int year;
    private int month;
    private String patente;

    public RaceTable() {
    }

    public int getRaceAmount() {
        return raceAmount;
    }

    public void setRaceAmount(int raceAmount) {
        this.raceAmount = raceAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

}
