package gui;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import javafx.stage.Stage;
import entities.Reponse;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.ServiceReponse;
import static utils.CommonController.setSceneContent;
import utils.MaConnexion;


public class AddReponseController implements Initializable {
    private Connection cnx;
    @FXML
    private DatePicker tf_dated;
    @FXML
    private TextField id_reclamationa;
    @FXML
    private TextField reponsea;
    @FXML
    private Button btsSMS;

    public AddReponseController(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    private TextField id_reclamation;

    private TextField dateR;

    private TextField reponse;

    @FXML
    private Button Ajout;
    @FXML
    private Button Af;
    

    private void AddReponse(ActionEvent event)throws SQLException, MessagingException{

        Ajout();
        id_reclamation.clear();
        dateR.clear();
        reponse.clear();
   

    }


/*
    private void EnrejistreBase(){

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();


        try {
            String sql = "INSERT INTO Reponse (`id_reclamation`, `dateR`, `reponse`) VALUES (?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.setString(1, id_reclamation.getText());
            st.setString(2, dateR.getText());

            st.setString(3, reponse.getText());




            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }


    }
*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void Afficher(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffReponse.fxml"));


        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Af.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


    @FXML
    public void Ajout() throws SQLException, MessagingException {
     /* java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();


        try {
            String sql = "INSERT INTO Reponse (`id_reclamation`, `dateR`, `reponse`) VALUES (?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.setString(1, id_reclamation.getText());
            st.setString(2, dateR.getText());

            st.setString(3, reponse.getText());




            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }*/

                if (id_reclamationa.getText().isEmpty() || reponsea.getText().isEmpty()){ Alert alert = new Alert(Alert.AlertType.WARNING); alert.setTitle("Champs manquants"); alert.setHeaderText(null); alert.setContentText("Veuillez remplir tous les champs !"); alert.showAndWait(); return; } 

                      ServiceReponse sp=new ServiceReponse();
          int id_reclamation =Integer.parseInt(id_reclamationa.getText());
          String reponse=reponsea.getText();
      
          Date dateR = java.sql.Date.valueOf(tf_dated.getValue());
     
         
 
   Reponse a = new Reponse(  id_reclamation, dateR,  reponse);
   sp.createOne(a);
        String email = sp.getEmail();
        sendMail(email);

    }
    
    public static void sendMail(String recepient) throws MessagingException{
        System.out.println("Prepared to send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.ssl.trust","*");

        String myEmailAccount="ing.rima.ben.hmida@gmail.com";
        String password="cfpitovjjijlozqo";
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
            message.setSubject("Reponse quicklines");
            message.setText("Bonjour cher(e) client(é),\n Votre reponse est le ! \n Bienvenu(e) parmi nous 🙂");
            return message;
                    } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        }

    @FXML
    private void SMS(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("message.fxml"));


        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btsSMS.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    }









