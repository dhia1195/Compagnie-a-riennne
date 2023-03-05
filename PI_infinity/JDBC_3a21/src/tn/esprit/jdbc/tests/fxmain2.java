package tn.esprit.jdbc.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class fxmain2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("C:\\Users\\dhia-\\Downloads\\PI_infinity\\JDBC_3a21\\src\\tn\\esprit\\jdbc\\gui\\AjouterS.fxml").toURI().toURL();
        System.out.println(url);
        Parent root=FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("CSS/newCascadeStyleSheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}


