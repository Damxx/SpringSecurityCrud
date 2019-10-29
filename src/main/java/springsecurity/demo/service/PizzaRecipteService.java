package springsecurity.demo.service;

import org.hibernate.boot.model.naming.ImplicitNameSource;

import springsecurity.demo.user.Przepis;
import springsecurity.demo.user.Skladniki;

public interface PizzaRecipteService {

	
	public Skladniki wybierz(int receptura, int ilosc_porcji) ;		
	
	 Skladniki italyPizza(int ilosc_porcji);
	 
	 Skladniki romePizza(int ilosc_porcji);
	
	 Skladniki chicagoPizza(int ilosc_porcji);
	
}
