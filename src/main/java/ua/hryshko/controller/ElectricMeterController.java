package ua.hryshko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.hryshko.model.ElectricMeter;
import ua.hryshko.model.History;
import ua.hryshko.service.ElectricMeterService;
import ua.hryshko.service.HistoryService;
import ua.hryshko.service.UserService;

@Controller
public class ElectricMeterController {

    @Autowired
    ElectricMeterService meterService;

    @Autowired
    UserService userService;

    @Autowired
    HistoryService historyService;


    @RequestMapping(value = "/create/electric-meter/{owner_id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String create(@PathVariable("owner_id") Long owner_id,
                         Model model) {

        model.addAttribute("owner_id", owner_id);
        model.addAttribute("electricMeter", new ElectricMeter());

        return "electric-meter-create";
    }

    @RequestMapping(value = "/create/electric-meter/{owner_id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String create(@PathVariable Long owner_id,
                         @ModelAttribute("electricMeter") ElectricMeter electricMeter) {

        electricMeter.setOwner(userService.readById(owner_id));
        electricMeter.setPayment(0f);

        meterService.addElectricMeter(electricMeter);

        return "redirect:/show/electric-meter/" + owner_id;
    }


    @RequestMapping(value = "/{owner_id}/update/electric-meter/{id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String update(@PathVariable Long id,
                         @PathVariable("owner_id") Long owner_id,
                         Model model) {

        ElectricMeter meter = meterService.readById(id);
        model.addAttribute("owner_id", owner_id);
        model.addAttribute("electricMeter", meter);

        return "electric-meter-edit";
    }

    @RequestMapping(value = "/{owner_id}/getresult/electric-meter/{id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String update(@PathVariable Long id,
                         @PathVariable("owner_id") Long owner_id,
                         @ModelAttribute("electricMeter") ElectricMeter meter) {
        ElectricMeter meterBd = meterService.readById(id);

        History history = new History();

        long newData = meter.getReading();
        long oldData = meterBd.getReading();
        float payment = meterBd.getPayment();

        history.setLastReading(oldData);
        history.setNewReading(newData);
        history.setConsumed(newData - oldData);
        history.setBalanceBeforePaid(payment);


        Float result = Float.valueOf(0);
        result = (float) ((newData - oldData) * 8.46);

        payment += result;

        history.setBalanceAfterPaid(payment);
        history.setMeterId(meterBd.getId());

        meterBd.setPayment(payment);
        meterBd.setReading(meter.getReading());


        meterService.update(meterBd);
        historyService.addHistory(history);


        return "redirect:/show/electric-meter/" + meterBd.getOwner().getId();
    }


    @RequestMapping(value = "/{owner_id}/payment/electric-meter/{id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String payment(@PathVariable Long id,
                          @PathVariable("owner_id") Long owner_id,
                          Model model) {

        ElectricMeter meter = meterService.readById(id);
        model.addAttribute("owner_id", owner_id);
        model.addAttribute("electricMeter", meter);

        return "electric-meter-payment";
    }

    @RequestMapping(value = "/{owner_id}/payment/electric-meter/{id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String pay(@PathVariable Long id,
                      @PathVariable("owner_id") Long owner_id,
                      @ModelAttribute("electricMeter") ElectricMeter meter) {

        History history = new History();

        ElectricMeter meterBd = meterService.readById(id);
        float payment = meterBd.getPayment();

        history.setMeterId(meterBd.getId());
        history.setBalanceBeforePaid(payment);


        //8,46

        if (payment < 0) {
            payment += meter.getPayment();
        }
        payment -= meter.getPayment();

        history.setBalanceAfterPaid(payment);

        meterBd.setPayment(payment);

        meterService.update(meterBd);
        historyService.addHistory(history);


        return "redirect:/show/electric-meter/" + meterBd.getOwner().getId();
    }


    @RequestMapping(value = "/delete/{id}/electric-meter/{electricMeter_id}")
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String deleteGasMeter(@PathVariable("electricMeter_id") Long electricMeter_id,
                                 @PathVariable("id") Long owner_id) {


        ElectricMeter meter = meterService.readById(electricMeter_id);
        meterService.removeElectricMeter(electricMeter_id);

        return "redirect:/show/electric-meter/" + meter.getOwner().getId();
    }


    @RequestMapping(value = "/show/electric-meter/{owner_id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String getGasometerList(@PathVariable long owner_id, Model model) {
        model.addAttribute("owner", userService.readById(owner_id));
        model.addAttribute("electricMeterList", meterService.getElectricMetersByUserId(owner_id));
        return "electric-meter-list";
    }

}
