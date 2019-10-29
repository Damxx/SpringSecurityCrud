package springsecurity.demo.serviceEmailCycleTime;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springsecurity.demo.entity.Workers_salary;
import springsecurity.demo.entity.Workers_salarySearch;

import java.util.List;
import java.util.Properties;


@Component
public class MailBoxForCron {
	
		
		private List<Workers_salary> workersList;
		private String user;
		
		private String addrese = "damian.jakub.padzik@gmail.com";
		
		
		
		public boolean setEmailParam(List<Workers_salary> workers_salaries, String user) {
			this.user = user;
			this.workersList = workers_salaries;
			sendEmail();
			
			return true;
		}
		
	

		public void setAddrese(String addrese) {
			this.addrese = addrese;
		}




		public void sendEmail(){
    	
        final String username = "karolklocx@gmail.com";
        final String password = "dp1998A@123";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("karolklocx@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(addrese)
            );
            message.setSubject("Witaj "+user.toUpperCase());
            message.setText("Czesc "+user+ "\n\n  Witaj oto lista wyciagnieta z bazy danych, ktora zaiwera obecnych pracownikow!\n\n\n Lista \n : "+workersList);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }}

