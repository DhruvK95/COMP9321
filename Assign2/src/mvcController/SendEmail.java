package mvcController;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class SendEmail {
	private String username;
	private String password;
	private Properties props;
	
	public SendEmail() {

		username = "makehotelsgreatagain@gmail.com";
		password = "m4ke_h0tels_gr8";

		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		
	}
	
	public void verificationMail(String user, String recipientMail, HttpServletRequest request){
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("makehotelsgreatagain@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(recipientMail));
					message.setSubject("Verification");
					String serverName = request.getServerName();
					int portNumber = request.getServerPort();
					String contextPath = request.getContextPath();
					System.out.println(serverName + ":" +portNumber + contextPath );
					String path = serverName.concat(":").concat(Integer.toString(portNumber)).concat(contextPath);
					message.setText("Please verify your account given the following link "+
							"http://" + path + "?verify="+user+"&action=login");

					Transport.send(message);

					System.out.println("Done");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
	}
	
	public void bookingMail(String recipientMail, HttpServletRequest request){
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("makehotelsgreatagain@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(recipientMail));
					message.setSubject("Verification");
					String serverName = request.getServerName();
					int portNumber = request.getServerPort();
					String contextPath = request.getContextPath();
					System.out.println(serverName + ":" +portNumber + contextPath );
					String path = serverName.concat(":").concat(Integer.toString(portNumber)).concat(contextPath);
					message.setText("Check you booking through the following link "+
							"http://" + path + "?booking=&action=checkBooking");

					Transport.send(message);

					System.out.println("Done");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
	}
}
