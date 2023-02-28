package tn.esprit.jdbc.API;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class PasswordResetEmail {
    private final String senderEmail = "zahmoulaini@gmail.com"; // Your email address
    private final String senderPassword = "loolforever123"; // Your email password
    private final String host = "smtp.gmail.com"; // Your email SMTP host
    private final int port = 587; // Your email SMTP port
    private final String subject = "Password Reset Request"; // Email subject
    private final String resetLink = "http://example.com/reset_password?token=abc123"; // Password reset link

    public void sendPasswordResetEmail(String recipientEmail) throws MessagingException {
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
        message.setText("Dear user,\n\nYou have requested to reset your password. Please click on the following link to reset your password:\n\n" + resetLink + "\n\nIf you did not request a password reset, please ignore this email.\n\nBest regards,\nThe Example Team");

        Transport.send(message);
    }
}