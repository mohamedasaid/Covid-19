package i.o.covid19.covid19tracker.controllers;

import i.o.covid19.covid19tracker.models.DataCountries;
import i.o.covid19.covid19tracker.services.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidService covidService;
    @GetMapping("/")
    public String home(Model model) {
        List<DataCountries> list = covidService.getDataList();
        int totalCase = list.stream().mapToInt(stat -> stat.getLatestData()).sum();
        int totalNewCase = list.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("dataCountries", list);
        model.addAttribute("totalCase", totalCase);
        model.addAttribute("totalNewCases", totalNewCase);



        return "home";

    }


}
