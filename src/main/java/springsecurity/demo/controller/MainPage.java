package springsecurity.demo.controller;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springsecurity.demo.validation.EasyEmailValid;
import springsecurity.demo.validation.ValidEmail;

@Controller
public class MainPage {

	
	private final Logger log = Logger.getLogger(getClass().getName());
	
	@RequestMapping("/")
	public String showHome(Model theModel) {
		
		EasyEmailValid easyEmailValid = new EasyEmailValid();
		
		theModel.addAttribute("email", easyEmailValid);
		
		return "strona_glowna";
	}
	
	@RequestMapping(value = "/dodaj", method = RequestMethod.GET)
	public String addEmail(@ModelAttribute(value = "email")  @Valid EasyEmailValid email, BindingResult result) {
		if(result.hasErrors()) {
			log.log(Level.WARNING,"Wadliwy email");
				return "redirect:/";}
		
		log.log(Level.WARNING, "Email prawidlowy, wiadomosc wysylana");
		
		
		return "redirect:/";
	}
	
}
