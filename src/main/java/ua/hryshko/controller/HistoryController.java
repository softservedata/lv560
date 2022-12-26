package ua.hryshko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.hryshko.service.ElectricMeterService;
import ua.hryshko.service.GasMeterService;
import ua.hryshko.service.HistoryService;

@Controller
public class HistoryController {

    @Autowired
    HistoryService historyService;
    @Autowired
    GasMeterService gasMeterService;
    @Autowired
    ElectricMeterService electricMeterService;

    @RequestMapping(value = "/history/gasmeter/{owner_id}", method = RequestMethod.GET)
    public String showHistoryGasMeter(@PathVariable Long owner_id, Model model) {
        Long user_id = gasMeterService.readById(owner_id).getOwner().getId();
        model.addAttribute("owner_id", user_id);
        model.addAttribute("HistoryList", historyService.findByOwner(owner_id));
        return "history";

    }


    @RequestMapping(value = "/history/electric-meter/{owner_id}", method = RequestMethod.GET)
    public String showHistoryElectricMeter(@PathVariable Long owner_id, Model model) {
        Long user_id = electricMeterService.readById(owner_id).getOwner().getId();
        model.addAttribute("owner_id", user_id);
        model.addAttribute("HistoryList", historyService.findByOwner(owner_id));
        return "electric-meter-history";

    }
}
