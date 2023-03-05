package tn.esprit.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.Role;
import tn.esprit.jdbc.services.ServiceUser;
import tn.esprit.jdbc.entities.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {

    @FXML
    private Button button_login;
    @FXML
    private Button button_registre;
    @FXML
    private TextField  login_mail;
    @FXML
    private PasswordField  login_password;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button forget;



    ServiceUser sp = new ServiceUser();
    public void openForgetPassScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forgetPass.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 382, 340);
        Stage stage = new Stage();
        stage.setTitle("Forgot Password");
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forget.setOnAction(ps->{
            try {
                openForgetPassScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button_login.setOnAction(login -> {
            String mail = login_mail.getText();
            String password = login_password.getText();

            User user = null;
            try {
                user = sp.authenticate(mail, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println(user.toString());
            if (user.getId() != 0) {

                Role role = user.getRole();

                switch (role.getRole()) {
                    case "admin":
                        FXMLLoader loaderadmin=new FXMLLoader(getClass().getResource("admin.fxml"));
                        Parent admin= null;
                        try {
                            admin = loaderadmin.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        AdminController ac=loaderadmin.getController();
                        ac.setAdmin(user);


                        button_login.getScene().setRoot(admin);


                        break;
                    case "client":
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("client.fxml"));
                        Parent root= null;
                        try {
                            root = loader.load();
                            ClientController cc=loader.getController();
                            cc.setClient(user);
                        } catch (IOException | SQLException e) {
                            throw new RuntimeException(e);
                        }

                        button_login.getScene().setRoot(root);




                        break;
                    case "chauffeur":
                        FXMLLoader loaderch=new FXMLLoader(getClass().getResource("chauffeur.fxml"));
                        root= null;
                        try {
                            root = loaderch.load();
                            ChauffeurController chc=loaderch.getController();
                            chc.setChauffeur(user);
                        } catch (IOException | SQLException e) {
                            throw new RuntimeException(e);
                        }

                        button_login.getScene().setRoot(root);
                        break;

                }
            } else {
                errorMessageLabel.setText("Invalid mail or password");



            }
        });

        button_registre.setOnAction(registre ->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            button_login.getScene().setRoot(root);
        });

    }

}