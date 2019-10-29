package springsecurity.demo.serviceEmailCycleTime;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import springsecurity.demo.entity.Workers_salary;

public class MailBoxForRecipte {
	
	
	private String addressee;
	String text_for_mail;
	
	
	public  MailBoxForRecipte(String addressee, String text_for_mail) {
		this.addressee = addressee;
		this.text_for_mail = text_for_mail;
		
		sendEmail();
		
	
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
                InternetAddress.parse(addressee)
        );
        message.setSubject("Witaj ");
        message.setText(text_for_mail);;

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
}}