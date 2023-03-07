package tn.esprit.main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.Reclamation;
import tn.esprit.jdbc.entities.Reponse;
import tn.esprit.jdbc.services.ServiceReclamation;
import tn.esprit.jdbc.services.ServiceReponse;
import tn.esprit.jdbc.utils.MaConnexion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button chauffeur;
    @FXML
    private Button gavion;
    @FXML
    private Button gsiege;
    @FXML
    private Button reclamation;
    @FXML
    private Button gmoy;
    @FXML
    private Button Af;
    @FXML
    private Button dvol;
    @FXML
    private Button gvol;


    private void AddReponse(ActionEvent event)throws SQLException, MessagingException {

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
        dvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        fetch.setOnAction(f->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fetch.getScene().setRoot(root);
        });
        chauffeur.setOnAction(ch->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("adminChauffeurDash.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            chauffeur.getScene().setRoot(root);

        });

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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("addReponse.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gavion.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouter2.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gsiege.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterS.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gmoy.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
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
