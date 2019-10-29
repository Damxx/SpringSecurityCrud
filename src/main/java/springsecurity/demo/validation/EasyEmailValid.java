package springsecurity.demo.validation;

import javax.validation.constraints.Email;

public class EasyEmailValid {

	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
