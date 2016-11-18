package elearning2.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSend {
private String SMTP_PORT="465";
private String SSL_FACTORY="javax.net.ssl.SSLSocketFactory";
private String SMTP_HOST_NAME="smtp.gmail.com";
private String contentType="text/html";

private Properties smtpProperties;
public MailSend(){
	System.out.println("inside MailSend");
	initProperties();
}
private void initProperties(){
	System.out.println("inside intiProp");
	smtpProperties=new Properties();
	smtpProperties.put("mail.smtp.host",SMTP_HOST_NAME);
	smtpProperties.put("mail.smtp.auth","true");
	smtpProperties.put("mail.debug","true");
	smtpProperties.put("mail.smtp.port",SMTP_PORT);
	smtpProperties.put("mail.smtp.socketFactory.port",SMTP_PORT);
	smtpProperties.put("mail.smtp.socketFactort.class",SSL_FACTORY);
	smtpProperties.put("mail.smtp.socketFactory.fallback","false");
	
	
	
	
}
public static String send(String to,final String from,final String pwd,String subject,String body){
	MailSend mailSend=new MailSend();
	try{
		Properties props=mailSend.getSmtpProperties();
		Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from,pwd);
				
			}
		});
		Message msg=new MimeMessage(session);
	    msg.setFrom(new InternetAddress(from));
	    msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to,false));
	    msg.setSubject(subject);
	    msg.setText(body);
	    msg.setSentDate(new Date());
	    Transport.send(msg);
	    System.out.println("message sent OK.");
	
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		return "fail";
		
	}
	return "sucess";
	
}
public Properties getSmtpProperties(){
	return smtpProperties;
	
}
public void setSmtpProperties(Properties smtpProperties){
	this.smtpProperties=smtpProperties;
}
}
