/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.entities.ReservationTransport;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ReservationTservice;
import tn.esprit.jdbc.utils.Context;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.jdbc.utils.CommonController.setSceneContent;


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
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button reclamation;
    @FXML
    private Button res;
    @FXML
    private Button rvol;
    @FXML
    private Button ajouter;
    static User user;

    public void setClient(User u) {
        user=u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ajouter.setOnAction(e->{
            ReservationTservice sp = new ReservationTservice();
            ReservationTransport a = new ReservationTransport(u.getId_moyent(),dated,datef,null,statut_chauffeur);
            sp.ajouter(a);
        });
        rvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("recherche.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RechercheController rc=loader.getController();

            rc.setClient(user);

            logout_button.getScene().setRoot(root);
        } );
        logout_button.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        reclamation.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("addReclamation.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AddReclamationController arc=loader.getController();
            arc.setClient(user);
            logout_button.getScene().setRoot(root);
        } );
        res.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLReservation.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FXMLReservationController rc=loader.getController();
            rc.setClient(user);
            logout_button.getScene().setRoot(root);
        } );
        fetch.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("client.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ClientController cc=loader.getController();
            try {
                cc.setClient(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            logout_button.getScene().setRoot(root);
        } );
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
        String dossierImages = "D:\\Studies\\PI\\src\\main\\resources\\tn\\esprit\\main\\assets";

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


        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLMoyenTransport.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FXMLMoyenTransportController orc=loader.getController();
        orc.setClient(user);
        btnALouer.getScene().setRoot(root);

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
