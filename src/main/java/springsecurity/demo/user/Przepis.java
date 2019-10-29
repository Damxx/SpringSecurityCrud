package springsecurity.demo.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;



public class Przepis {
	
	@Range(min = 1,max = 50)
	private int sztuki;
	
	@org.hibernate.validator.constraints.Email
	private String email;
	
	private int rodzaj;
	
	public Przepis(int sztuki, String email,int rodzaj) {
		this.sztuki = sztuki;
		this.email = email;
		this.rodzaj=rodzaj;
		
	}
	public Przepis() {
		// TODO Auto-generated constructor stub
	}
	public int getSztuki() {
		return sztuki;
	}
	public void setSztuki(int sztuki) {
		this.sztuki = sztuki;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRodzaj() {
		return rodzaj;
	}
	public void setRodzaj(int rodzaj) {
		this.rodzaj = rodzaj;
	}
	
	
}
