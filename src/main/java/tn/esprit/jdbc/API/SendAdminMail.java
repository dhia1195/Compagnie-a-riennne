package tn.esprit.jdbc.API;

import tn.esprit.jdbc.entities.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendAdminMail{
        private final String senderEmail = "nidhal.barek@esprit.tn"; // Your email address
        private final String senderPassword = "223AMT0397"; // Your email password
        private final String host = "smtp.gmail.com"; // Your email SMTP host
        private final int port = 587; // Your email SMTP port
        private final String subject = "Password Reset Request"; // Email subject


        public void sendAdminEmail(String recipientEmail) throws MessagingException {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText("Bonjour cher(e) Admine,\n Votre ajout a été effectué avec succès ! \n Cordialement 🙂");

            Transport.send(message);
        }
}
