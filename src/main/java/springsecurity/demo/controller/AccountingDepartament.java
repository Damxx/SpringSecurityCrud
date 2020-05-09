package springsecurity.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.soap.SOAPBinding;
import javax.management.RuntimeErrorException;
import javax.validation.Valid;
import javax.validation.constraints.Email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import antlr.collections.List;
import springsecurity.demo.entity.*;
import springsecurity.demo.user.Przepis;
import springsecurity.demo.user.Skladniki;
import springsecurity.demo.service.PizzaRecipteService;
import springsecurity.demo.service.UserService;
import springsecurity.demo.service.UserServiceImpl;
import springsecurity.demo.serviceEmailCycleTime.MailBoxForCron;
import springsecurity.demo.serviceEmailCycleTime.MailBoxForRecipte;

@Controller
@RequestMapping("/ksiegowosc")
public class AccountingDepartament {

    protected final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    UserService userservice;

    @Autowired
    MailBoxForCron javaMailUtil;

    @Autowired
    PizzaRecipteService pizzaRecipteService;

    @RequestMapping("")
    public String showbook(Model theModel, @RequestParam(value = "Searchtext", required = false) String Searchtext) {

        if (Searchtext == null) {

            java.util.List<Workers_salary> workersList = userservice.getWorkers_salaries();

            theModel.addAttribute("workers", workersList);

            theModel.addAttribute("liczba_pracownikow", workersList.size());

        } else {
            java.util.List<Workers_salarySearch> theWorkers_salarySearch = userservice.getSearch(Searchtext);
            log.log(Level.INFO, "Wyszukano ", userservice.getSearch(Searchtext));
            log.log(Level.INFO, "Liczba elementow znalezionych ", theWorkers_salarySearch.size());
            theModel.addAttribute("workers", theWorkers_salarySearch);

            theModel.addAttribute("liczba_pracownikow", theWorkers_salarySearch.size());
        }
        return "ksiegowosc";

    }

    @RequestMapping(value = "/email")
    public String Email(@RequestParam(value = "email_kierownik", required = false) String email, Authentication authentication) {


        java.util.List<Workers_salary> workersList = userservice.getWorkers_salaries();


        if (email != null) {
            log.log(Level.INFO, "Wysylanie danych do " + email);
            javaMailUtil.setEmailParam(workersList, "user");
            javaMailUtil.setAddrese(email);
        } else {

            javaMailUtil.setEmailParam(workersList, "user ");

            log.log(Level.INFO, "Wyslano mail do glownego kierownika");

        }

        return "redirect:/ksiegowosc";
    }


    @RequestMapping(value = "/showforupdate", method = RequestMethod.GET)
    public String update(@RequestParam(value = "customerId", required = true) int id, Model theModel) {

        theModel.addAttribute("Workers", userservice.getWorkers_salary(id));

        return "form-add";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteWorkers(@RequestParam(value = "customerId", required = true) int id_workers) {
        userservice.deteleWorkers(id_workers);
        return "redirect:/ksiegowosc";

    }

    @PostMapping("/saveWorkers")
    public String addNew(@ModelAttribute("Workers") Workers_salary workers_salary) {

        userservice.addNew(workers_salary);

        return "redirect:/ksiegowosc";
    }

    @RequestMapping("/showforadd")
    public String show(Model themModel) {

        Workers_salary theWorkers_salary = new Workers_salary();

        themModel.addAttribute("Workers", theWorkers_salary);

        return "form-add";

    }


}










