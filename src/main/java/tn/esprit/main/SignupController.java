package tn.esprit.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tn.esprit.jdbc.services.ServiceRole;
import tn.esprit.jdbc.services.ServiceUser;
import tn.esprit.jdbc.entities.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignupController implements Initializable {
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

    ServiceRole sr = new ServiceRole();
    ServiceUser su = new ServiceUser();
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user=new User();
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(homme, femme);

// add event listener to toggle group
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (toggleGroup.getSelectedToggle() != null) {
                // get selected radio button text
                String selectedText = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                user.setSexe(selectedText);
                System.out.println("Selected option: " + selectedText);
            }
        });
        boutton_signup.setOnAction(sigup -> {
            try {
                boolean v=true;
                while (v) {



                    if (su.findByMail(email.getText()).getId() != 0) {
                        v = false;
                        email_existe.setText("email existe deja");

                    }
                    else if(!Pattern.compile(EMAIL_PATTERN).matcher(email.getText()).matches()){
                        v = false;
                        email_existe.setText("email invalide");

                    }
                    else
                        email_existe.setText("");

                    if (!Objects.equals(password.getText(), conf_password.getText())) {
                        v = false;
                        pass_conf.setText("mot de passe n'est pas conforme");

                    }
                    else pass_conf.setText("");

                    user.setNom(nom.getText());
                    user.setPrenom(prenom.getText());
                    user.setEmail(email.getText());
                    user.setPassword(password.getText());


                    user.setRole(sr.findByRole("client"));
                    if (v) {
                        su.createOne(user);
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("client.fxml"));
                        Parent root= null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ClientController cc=loader.getController();
                        cc.setClient(user);
                        password.getScene().setRoot(root);

                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }



            System.out.println(user.toString());


        });

    }
}
