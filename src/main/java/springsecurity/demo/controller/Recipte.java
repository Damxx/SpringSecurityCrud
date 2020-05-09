package springsecurity.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springsecurity.demo.service.PizzaRecipteService;
import springsecurity.demo.serviceEmailCycleTime.MailBoxForRecipte;
import springsecurity.demo.user.Przepis;
import springsecurity.demo.user.Skladniki;

@Controller
@RequestMapping("/przepis")
public class Recipte {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    PizzaRecipteService pizzaRecipteService;


    @RequestMapping(value = "/receptura", method = RequestMethod.GET)
    String przepis(Model theModel) {
        Przepis przepis = new Przepis();

        theModel.addAttribute("przepis", przepis);

        return "store";
    }


    @PostMapping(value = "/receptura")
    String proccesPrzepis(Model theModel, @ModelAttribute("przepis") @Valid Przepis przepis
            , BindingResult result) {

        if (result.hasErrors()) {

            log.log(Level.INFO, "Formularz ma bledy ", result.getFieldError());
            return "store";
        }
        log.log(Level.INFO, "Ilosc pizzy " + przepis.getSztuki() + "sztuk \n\n Rodzaju : " + przepis.getRodzaj());
        Skladniki skladniki =
                pizzaRecipteService.wybierz(przepis.getRodzaj(), przepis.getSztuki());

        if (przepis.getEmail() != null) {
            MailBoxForRecipte boxForRecipte = new MailBoxForRecipte(przepis.getEmail(), skladniki.toString());
        }

        theModel.addAttribute("skladniki", skladniki);


        return "store";
    }


}
