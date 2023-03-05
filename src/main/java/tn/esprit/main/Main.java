package tn.esprit.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.esprit.jdbc.API.PasswordResetEmail;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ServiceUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Infinity");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException, MessagingException {
        launch();
    }
}