/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.MoyenTransport;
import entities.ReservationTransport;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.MoyenTransportService;
import services.ReservationTservice;
import static utils.CommonController.setSceneContent;
import utils.Context;

/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLLouerVController implements Initializable {

    /**
     * Initializes the controller class.
     */
    MoyenTransport u = (MoyenTransport) Context.getInstance().getContextObject("moyentransport");
    Date dated = (Date) Context.getInstance().getContextObject("DateD");
    Date datef = (Date) Context.getInstance().getContextObject("DateF");
    boolean statut_chauffeur = (boolean) Context.getInstance().getContextObject("statut_chauffeur");


    @FXML
    private Button btnALouer;
    @FXML
    private ImageView IDimage;
    @FXML
    private Label affichage;
    @FXML
    private Button btnBack;
    @FXML
    private TextField emailtxt;
    @FXML
    private ImageView cheminimg1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("test " + u);
        CheckBox checkBox = new CheckBox();
        if (u.getDescription() != null) {
             if (statut_chauffeur=true) {
                             long diffInMillies = Math.abs(datef.getTime() - dated.getTime());
            long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            affichage.setText("Modele de voiture :  " + u.getModele() + "\n"
                    + "description :  " + u.getDescription() + "\n" + "prix par jour :  " + u.getPrix_location_jour()+ "\n"
                    + "\n" +" frais chauffeur: "+u.getFrais_chauffeur() + "\n Prix total: "+daysBetween*(u.getPrix_location_jour()+u.getFrais_chauffeur()));
             }
             else{
            long diffInMillies = Math.abs(datef.getTime() - dated.getTime());
            long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            affichage.setText("Modele de voiture :  " + u.getModele() + "\n"
                    + "description :  " + u.getDescription() + "\n" + "prix par jour :  " + u.getPrix_location_jour()+ "\n"
                    + "\n Prix total: "+daysBetween*u.getPrix_location_jour());
        } }else {
            affichage.setText("Info non disponible");
        }
        String dossierImages = "C:\\pidev\\ResevationTransport\\images\\";

        File dossier = new File(dossierImages);
        File[] fichiers = dossier.listFiles();
        System.out.println(u.getPhoto());
        for (File fichier : fichiers) {
        String nomFichier = fichier.getName();
        if (nomFichier.startsWith(u.getPhoto()) && nomFichier.endsWith(".JPB")||nomFichier.startsWith(u.getPhoto()) && nomFichier.endsWith(".PNG")) {
            String cheminImage1 = fichier.toURI().toString();
            Image image = new Image(cheminImage1);
            cheminimg1.setImage(image);
            break;

    }
        }
    }

    @FXML
    private void back(ActionEvent event) {
    
   
          try {  
            setSceneContent("FXMLMoyenTransport");
        } catch (IOException ex) {
            Logger.getLogger(FXMLMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    public static void sendMail(String recepient) throws MessagingException{
        System.out.println("Prepared to send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myEmailAccount="yosraba90@gmail.com";
        String password="oyvyjbjbdlxzueyy";
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myEmailAccount,password);
                
            }
        });
        
        Message message=prepareMessage(session,myEmailAccount,recepient);
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }
        private static Message prepareMessage(Session session, String myEmailAccount,String recepient){
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reservation du moyen transport");
            message.setText("Bonjour cher(e) client(e),\n Suite à votre demande, veuillez trouver ci-dessous notre RIB  888555693742014 Attijari bank");
            return message;
                    } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        }

    @FXML
    private void payer(ActionEvent event) throws MessagingException {
   String email=emailtxt.getText();
   sendMail(email);
    }
   
  
    }
