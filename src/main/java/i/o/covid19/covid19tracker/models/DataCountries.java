package i.o.covid19.covid19tracker.models;

public class DataCountries {

    private String state;
    private String country;
    private int latestData;
    private int diffFromPrevDay;


    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestData() {
        return latestData;
    }

    public void setLatestData(int latestData) {
        this.latestData = latestData;
    }

    @Override
    public String toString() {
        return "DataCountries{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestData=" + latestData +
                '}';
    }
}
