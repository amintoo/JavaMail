package events.by.abylsen;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SmtpTest {

	public static void main(String[] args) throws Exception {

		String host = "smtp.gmail.com";
		String from = "xxxx@gmail.com";
		String to = "xxxx@gmail.com";
		// xxxx before @gmail.com
		String username = "xxxx";
		String password = "xxxx";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Hello");
		message.setText("Hello World");

		Transport tr = session.getTransport("smtp");
		tr.connect(host, username, password);
		message.saveChanges();

		// tr.send(message);
		/**
		 * Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme
		 * pour une seule adresse...
		 */

		tr.sendMessage(message, message.getAllRecipients());
		tr.close();

	}
}