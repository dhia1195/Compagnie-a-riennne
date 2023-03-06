/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;

/**
 *
 * @author firas
 */
public class NewMain extends Application{
    Parent parent;
    Stage stage;
     public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        try {
            this.stage = primaryStage;
           
            Parent root = FXMLLoader.load(getClass().getResource("/gui/addReponse.fxml"));
           
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   }