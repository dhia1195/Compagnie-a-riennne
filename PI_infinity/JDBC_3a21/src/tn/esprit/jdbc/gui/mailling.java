package tn.esprit.jdbc.gui;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sun.rmi.transport.Transport;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javafx.scene.control.Alert;
import tn.esprit.jdbc.entities.Avion;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;

public class mailling {
    @FXML
    private TextField mail;

   /* public void sendmail(){

        Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.user","");
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.debug",true);
        props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback",false);

        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setText("Your OTP is " + mail.getText());
            message.setSubject("OTP For your Neftola Account");
            message.setFrom(new InternetAddress(""));
            message.addRecipient(RecipientType.TO, new InternetAddress(Email.getText().trim()));
            message.saveChanges();
            try
            {
                Avion avion = session.getTransport("smtp");
                avion.connect("smtp.gmail.com","","");
                avion.sendMessage(message, message.getAllRecipients());
                avion.close();



                JOptionPane.showMessageDialog(null,"OTP has send to your Email id");
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Please check your internet connection");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e);
        }*/
    }
//    public static void sendEmail(String recipient, String subject, String content) {
//        try {
//            // 1. Configuration des propriétés de la session
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//
//            // 2. Création de la session
//            Session session = Session.getInstance(props, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication("your_email_address@gmail.com", "your_email_password");
//                }
//            });
//
//            // 3. Création de l'objet message
//            Message message = new MIMEMessage(session);
//            message.setFrom(new InternetAddress("your_email_address@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//            message.setSubject(subject);
//            message.setText(content);
//
//            // 4. Envoi du message
//            Transport.send(message);
//
//            // 5. Affichage d'une alerte pour informer l'utilisateur que l'e-mail a été envoyé avec succès
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("E-mail envoyé");
//            alert.setHeaderText(null);
//            alert.setContentText("Votre e-mail a été envoyé avec succès à " + recipient);
//            alert.showAndWait();
//        } catch (Exception e) {
//            // En cas d'erreur, affichage d'une alerte pour informer l'utilisateur
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Une erreur s'est produite lors de l'envoi de l'e-mail.\nVeuillez réessayer plus tard.");
//            alert.showAndWait();
//            e.printStackTrace();
//        }
//    }



































//    public static void sendEmail(String recipient, String subject, String content) throws MessagingException {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("dhoudhou24@gmail.com", "");
//            }
//        });
//
//        Message message = new MIMEMessage(session);
//        message.equals(new InternetAddress("dhoudhou24@gmail.com"));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//        message.setSubject(subject);
//        message.setText(content);
//
//        Transport.send(message);

    /*private String username="dhoudhou24@gmail.com";
private String password="dhiaboudali1";
public void envoyer(){}


        public static void main (String[] args){
}
}*/
