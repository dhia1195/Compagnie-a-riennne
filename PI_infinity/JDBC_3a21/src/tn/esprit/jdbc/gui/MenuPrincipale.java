package tn.esprit.jdbc.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipale extends Application {

    @FXML
    private Button buttonAvion;
    @FXML
    private Button buttonSiege;



        @Override
        public void start(Stage primaryStage) throws Exception {
            // Charger les fichiers FXML pour chaque page
            FXMLLoader loaderPage1 = new FXMLLoader(getClass().getResource("Ajouter2.fxml"));
            Parent page1 = loaderPage1.load();

            FXMLLoader loaderPage2 = new FXMLLoader(getClass().getResource("AjouterS.fxml"));
            Parent page2 = loaderPage2.load();

            // Créer les boutons pour chaque page
            Button buttonAvion = new Button("Gestion Avion");
            buttonAvion.setOnAction(e -> primaryStage.setScene(new Scene(page1)));

            Button buttonSiege = new Button("Gestion Siege");
            buttonSiege.setOnAction(e -> primaryStage.setScene(new Scene(page2)));

            // Créer le menu Accueil
            VBox root = new VBox();
            root.getChildren().addAll(buttonAvion, buttonSiege);

            // Afficher le menu Accueil
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

