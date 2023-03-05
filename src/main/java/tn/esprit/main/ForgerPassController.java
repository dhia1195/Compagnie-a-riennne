package tn.esprit.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.esprit.jdbc.API.PasswordResetEmail;
import tn.esprit.jdbc.services.ServiceUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ForgerPassController implements Initializable {
    @FXML
    private TextField mail;
    @FXML
    private Label erreur;
    @FXML
    private Button envoyer;


    ServiceUser su=new ServiceUser();
    PasswordResetEmail pr=new PasswordResetEmail();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        envoyer.setOnAction(reset->{
            try {
                if(su.findByMail(mail.getText()).getId()!=0){
                    pr.sendPasswordResetEmail(mail.getText(),su.findByMail(mail.getText()));
                    envoyer.getScene().getWindow().hide();
                }
                else
                    erreur.setText("wrong email");


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        });


    }
}

