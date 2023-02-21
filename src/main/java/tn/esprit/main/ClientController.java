package tn.esprit.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tn.esprit.jdbc.entities.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientController implements Initializable {
    @FXML
    private Button logout_button;
    @FXML
    private Label username;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField conf_password;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private Button boutton_signup;
    @FXML
    private Label email_existe;
    @FXML
    private Label pass_conf;

    public void setClient(User u) {
        username.setText(u.getNom());
        nom.setText(u.getNom());
        prenom.setText(u.getNom());
        email.setText(u.getNom());






    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    }

}
