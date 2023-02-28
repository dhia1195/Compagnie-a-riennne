package tn.esprit.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
public class ClientController implements Initializable {
    @FXML
    private Button logout_button;
    @FXML
    private Button modifier;
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
    private TextField role;
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
    ServiceUser su =new ServiceUser();
    static User user =null;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public void setClient(User u) throws SQLException {
        u=su.findByMail(u.getEmail());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        email.setText(u.getEmail());
        user=u;
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

        modifier.setOnAction(logout ->  {
            try {
                boolean v=true;
                while (v) {
                    if (!su.findByMail(email.getText()).getEmail().equals(user.getEmail())&&su.findByMail(email.getText()).getId()!=0 ) {
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
                    if (v) {
                        su.updateOne(user, user.getId());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } );
    }
}