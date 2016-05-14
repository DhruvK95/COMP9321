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

	public SendEmail(String user, String recipientMail, HttpServletRequest request) {

		final String username = "makehotelsgreatagain@gmail.com";
		final String password = "m4ke_h0tels_gr8";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

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
			message.setSubject("Testing Subject");
			String serverName = request.getServerName();
			int portNumber = request.getServerPort();
			String contextPath = request.getContextPath();
			System.out.println(serverName + ":" +portNumber + contextPath );
			String path = serverName.concat(":").concat(Integer.toString(portNumber)).concat(contextPath);
			message.setText("Please verify your account given the following link "+
					"http://" + path + "?verify="+user);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}