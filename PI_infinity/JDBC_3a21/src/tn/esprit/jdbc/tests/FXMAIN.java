package tn.esprit.jdbc.tests;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FXMAIN extends Application {


    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws IOException {


       //URL url = new File("C:\\Users\\dhia-\\Downloads\\PI_infinity\\JDBC_3a21\\src\\tn\\esprit\\jdbc\\gui\\Ajouter2.fxml").toURI().toURL();
        URL url = new File("C:\\Users\\dhia-\\Downloads\\PI_infinity\\JDBC_3a21\\src\\tn\\esprit\\jdbc\\gui\\AjouterS.fxml").toURI().toURL();
        //URL url = new File("C:\\Users\\dhia-\\Downloads\\PI_infinity\\JDBC_3a21\\src\\tn\\esprit\\jdbc\\gui\\DoIT.fxml").toURI().toURL();
        System.out.println(url);

        Parent root = FXMLLoader.load(url);

        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root, Color.TRANSPARENT));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();



        window=primaryStage;


    }

    public static Stage getWindow() { return window;}

    public static void main(String[] args) {
        launch();
    }
}

