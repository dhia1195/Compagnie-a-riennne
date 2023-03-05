import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailAttachment {

	public static void main(String[] args) {
		Properties props;
		Session session;
		MimeMessage message;

		props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("max@dev-albedu.org", "java");
			}
		};
		
		session = Session.getInstance(props, auth);

		try {

			InternetAddress[] recipients = new InternetAddress[1];
			recipients[0] = new InternetAddress("admin@dev-albedu.org");
			//recipients[1] = new InternetAddress("recipient1@hotmail.com");
			//recipients[2] = new InternetAddress("recipient2@domainname.com");
			//recipients[3] = new InternetAddress("recipient3@somedomain.ch");
			//recipients[4] = new InternetAddress("recipient4@gtv.org");
			// ...

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress("max@dev-albedu.org"));
			message.addRecipients(Message.RecipientType.TO, recipients);
			message.setSubject("The First Test Email");
			//message.setText("This is the first test email. ");
			
			BodyPart attachment1 = new MimeBodyPart();
			attachment1.setDataHandler(new DataHandler(new FileDataSource("pic.png")));
			attachment1.setFileName("pic.png");
			
			BodyPart attachment2 = new MimeBodyPart();
			attachment2.setDataHandler(new DataHandler(new FileDataSource("file.txt")));
			attachment2.setFileName("file.txt");
			
			BodyPart emailText = new MimeBodyPart();
			emailText.setText("This is the first test email. ");
			
			Multipart multipartContent = new MimeMultipart();
			multipartContent.addBodyPart(attachment1);
			multipartContent.addBodyPart(attachment2);
			multipartContent.addBodyPart(emailText);
			
			message.setContent(multipartContent);

			Transport.send(message);
			
			System.out.println("Email sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
