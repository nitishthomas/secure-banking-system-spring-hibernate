package sbs.src.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sbs.src.form.Merchanttransaction;



public class SendMailSSL {
	
	public static boolean customerMail(Merchanttransaction merchanttransaction, String customerEmail){
	
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");

	Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			//	return new PasswordAuthentication("breakingaaronpaul@gmail.com","breaking_bad");
				return new PasswordAuthentication("breakingaaronpaul@gmail.com","breaking_bad");
			}
		});

	try {
		
		 long timeSeed = System.nanoTime(); 

	        double randSeed = Math.random() * 1000; 

	        long midSeed = (long) (timeSeed * randSeed);
	                                                     
	        String s = midSeed + "";
	        String subStr = s.substring(0,6);

	        int timeseed = Integer.parseInt(subStr);		     

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("breakingaaronpaul@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(customerEmail));
		message.setSubject("Merchant transaction");
		message.setText("Merchant has started a transaction of amount" + merchanttransaction.getAmount() + "\n" + "Please "
				+ "provide the following OTP "
				+ "and your bank's "
				+ "username after encrypting using"
				+ " your key"  +
				"\n\n " + timeseed);

		Transport.send(message);

		System.out.println("Done");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
	return true;
	}

	
}
