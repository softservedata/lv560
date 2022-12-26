package ua.hryshko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.hryshko.model.GasMeter;
import ua.hryshko.model.History;
import ua.hryshko.service.GasMeterService;
import ua.hryshko.service.HistoryService;
import ua.hryshko.service.UserService;



@Controller
public class GasMeterController {

    @Autowired
    GasMeterService gasMeterService;

    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "/create/gasmeter/{owner_id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String create(@PathVariable Long owner_id, Model model) {

        model.addAttribute("owner_Id", owner_id);
        model.addAttribute("gasMeter", new GasMeter());

        return "gasometer_create";
    }

    @RequestMapping(value = "/create/gasmeter/{owner_id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String create(@PathVariable Long owner_id, @ModelAttribute("gasMeter") GasMeter gasMeter) {

        gasMeter.setOwner(userService.readById(owner_id));
        gasMeter.setPayment(0f);

        gasMeterService.addGasMeter(gasMeter);

        return "redirect:/show/gasometers/" + owner_id;
    }


    @RequestMapping(value = "/{owner_id}/update/gasometer/{id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String update(@PathVariable Long id,
                         @PathVariable("owner_id") Long owner_id,
                         Model model) {

        GasMeter gasMeter = gasMeterService.readById(id);
        model.addAttribute("owner_id",owner_id);
        model.addAttribute("gasMeter", gasMeter);

        return "gasometer_edit";
    }

    @RequestMapping(value = "/{owner_id}/getresult/{id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String update(@PathVariable Long id,
                         @PathVariable("owner_id") Long owner_id,
                         @ModelAttribute("gasMeter") GasMeter gasMeter) {
        GasMeter gasMeterBd = gasMeterService.readById(id);

        History history = new History();

        long newData = gasMeter.getReading();
        long oldData = gasMeterBd.getReading();
        float payment = gasMeterBd.getPayment();

        history.setLastReading(oldData);
        history.setNewReading(newData);
        history.setConsumed(newData-oldData);
        history.setBalanceBeforePaid(payment);


        Float result = Float.valueOf(0);
        if (newData - oldData < 250) {
            result = (float) ((newData - oldData) * 1.44);
        } else result = (float) ((newData - oldData) * 1.68);

        payment+=result;

        history.setBalanceAfterPaid(payment);
        history.setMeterId(gasMeterBd.getId());

        gasMeterBd.setPayment(payment);
        gasMeterBd.setReading(gasMeter.getReading());


        gasMeterService.update(gasMeterBd);
        historyService.addHistory(history);


        return "redirect:/show/gasometers/"+ gasMeterBd.getOwner().getId();
    }




    @RequestMapping(value = "/{owner_id}/payment/{id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String payment(@PathVariable Long id,
                          @PathVariable("owner_id") Long owner_id,
                          Model model) {

        GasMeter gasMeter = gasMeterService.readById(id);
        model.addAttribute("owner_id",owner_id);
        model.addAttribute("gasMeter", gasMeter);

        return "payment";
    }

    @RequestMapping(value = "/{owner_id}/payment/{id}", method = RequestMethod.POST)
    @PostAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String pay(@PathVariable Long id,
                      @PathVariable("owner_id") Long owner_id,
                      @ModelAttribute("gasMeter") GasMeter gasMeter) {

        History history = new History();

        GasMeter gasMeterBd = gasMeterService.readById(id);
        float payment = gasMeterBd.getPayment();

        history.setMeterId(gasMeterBd.getId());
        history.setBalanceBeforePaid(payment);


        if(payment<0){
            payment+=gasMeter.getPayment();
        } payment -= gasMeter.getPayment();

        history.setBalanceAfterPaid(payment);

        gasMeterBd.setPayment(payment);

        gasMeterService.update(gasMeterBd);
        historyService.addHistory(history);


        return "redirect:/show/gasometers/"+ gasMeterBd.getOwner().getId();
    }



    @RequestMapping(value = "/delete/{id}/gasometer/{gasMeter_id}")
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String deleteGasMeter(@PathVariable("gasMeter_id") Long gasMeter_id,
                                 @PathVariable("id") Long owner_id) {


        GasMeter gasMeter = gasMeterService.readById(gasMeter_id);
        gasMeterService.removeGasMeter(gasMeter_id);

        return "redirect:/show/gasometers/"+gasMeter.getOwner().getId();
    }


    @RequestMapping(value = "/show/gasometers/{owner_id}", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('user')  or #owner_id == authentication.principal.id")
    public String getGasometerList(@PathVariable long owner_id, Model model) {
        model.addAttribute("owner", userService.readById(owner_id));
        model.addAttribute("gasMeterList", gasMeterService.getGasMetersByUserId(owner_id));
        return "gasometer_list";
    }


}
