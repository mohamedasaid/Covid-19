package i.o.covid19.covid19tracker.services;

import i.o.covid19.covid19tracker.models.DataCountries;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidService {

    private static String uri = "https://covid19.mathdro.id/api";
    private static String url2 = "https://covid19.mathdro.id/api/countries";
    private static String url =  "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/who_covid_19_situation_reports/who_covid_19_sit_rep_time_series/who_covid_19_sit_rep_time_series.csv";
    private static String url3 = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";



    private List<DataCountries> list = new ArrayList<>();

    public List<DataCountries> getDataList() {
        return list;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void  getDATA () throws IOException, InterruptedException {
        List<DataCountries> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> response =
                client.send(req, HttpResponse.BodyHandlers.ofString());


        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records) {
            DataCountries data = new DataCountries();
            data.setState(record.get("Province/State"));
            data.setCountry(record.get("Country/Region"));

            int last = Integer.parseInt(record.get(record.size()-1));
            int sLast = Integer.parseInt(record.get(record.size()-2));
            data.setDiffFromPrevDay(last-sLast);
            data.setLatestData(last);
            newStats.add(data);
           // System.out.println(data);

        }
       this.list = newStats;


    }
}
