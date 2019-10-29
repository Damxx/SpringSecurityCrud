package springsecurity.demo.service;

import org.springframework.stereotype.Service;

import springsecurity.demo.user.Przepis;
import springsecurity.demo.user.Skladniki;

@Service
public class PizzRecipteServiceImp implements PizzaRecipteService {


	Skladniki skladniki = new Skladniki();
	
	private final static int porcja = 230;
	
	
	@Override
	public Skladniki wybierz(int receptura, int ilosc_porcji) {
		
		
		switch (receptura) {
		case 1:
			
			System.out.println("wybrano 1 recepture");
			return italyPizza(ilosc_porcji);
			
		case 2:
			System.out.println("Wybrano 2 receptura");
			return romePizza(ilosc_porcji);
			
			
		case 3:
			System.out.println("Wybrano 3 receptura");
			return chicagoPizza(ilosc_porcji);
			
			
			
		
			
		}
	
		
		
		
		return null;
	}
	
	
	
	@Override
	public Skladniki italyPizza(int ilosc_porcji) {
		
		
		int calosc_ciasta=0;
		
		calosc_ciasta = ilosc_porcji*porcja;
	
		skladniki.setMaka(calosc_ciasta);
		skladniki.setDrozdze(calosc_ciasta);
		skladniki.setWoda(calosc_ciasta);
		skladniki.setSol(calosc_ciasta);
		
		System.out.println(skladniki);
		
		return skladniki;
	}



	@Override
	public Skladniki romePizza(int ilosc_porcji) {

		int calosc_ciasta=0;
		
		calosc_ciasta = ilosc_porcji*porcja;

		skladniki.setMaka(calosc_ciasta+20);
		skladniki.setDrozdze(calosc_ciasta+2);
		skladniki.setWoda(calosc_ciasta-30);
		skladniki.setSol(calosc_ciasta+3);
		
		
		
		System.out.println(skladniki);
		
		return skladniki;
	}



	@Override
	public Skladniki chicagoPizza(int ilosc_porcji) {	
		
		int calosc_ciasta=0;
		
		calosc_ciasta = ilosc_porcji*porcja;

		skladniki.setMaka(calosc_ciasta-19);
		skladniki.setDrozdze(calosc_ciasta+15);
		skladniki.setWoda(calosc_ciasta+20);
		skladniki.setSol(calosc_ciasta+19);
		
		System.out.println(skladniki);
		
		return skladniki;
	}






}
