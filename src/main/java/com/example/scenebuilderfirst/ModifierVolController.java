package com.example.scenebuilderfirst;

import entities.Escale;
import entities.Vol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServiceEscale;
import services.ServiceVol;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class ModifierVolController implements Initializable {
    @FXML
    private DatePicker picker;
    @FXML
    private TextField aero_arrmod;

    @FXML
    private TextField aero_depmod;

    @FXML
    private Button ajout;

    @FXML
    private Button annmod;

    @FXML
    private TextField h_arrmod;

    @FXML
    private TextField h_depmod;

    @FXML
    private RadioButton checkboxAvec;

    @FXML
    private RadioButton checkboxSans;
    @FXML
    private Button buttonNext;

    @FXML
    private Label labelMessage;



    @FXML
    private TextField j_volmod;

    @FXML
    private Button modifier;

    @FXML
    private TextField num_volmod;

    @FXML
    private Button suppression;

    @FXML
    private Button validmod;
    @FXML
    private Label labelajesc;
    @FXML
    void onCheckboxAvecClicked(ActionEvent event) {
        if (checkboxAvec.isSelected()) {
            buttonNext.setVisible(true);
            labelMessage.setVisible(false);
        } else {
            buttonNext.setVisible(false);
        }
    }

    @FXML
    void onCheckboxSansClicked(ActionEvent event) {
        if (checkboxSans.isSelected()) {
            labelMessage.setText("Pas d'escale");
            labelMessage.setVisible(true);
            buttonNext.setVisible(false);
        } else {
            labelMessage.setVisible(false);
        }
    }

    static Vol v = null;
    Vol vl = new Vol();
ServiceEscale se = new ServiceEscale();
ServiceVol sv = new ServiceVol();
    public void setVol(Vol vol){
        aero_arrmod.setText(vol.getAero_arrivee());
        aero_depmod.setText(vol.getAero_depart());
        //id_avmod.setText(String.valueOf(vol.getId_avion()));

        j_volmod.setText(vol.getJour_vol());


        num_volmod.setText(String.valueOf(vol.getNum_vol()));
        h_arrmod.setText(vol.getHeure_arrivee());
        h_depmod.setText(vol.getHeure_depart());



    }
    public void AjoutEscScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listesc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 569, 400);
        Stage stage = new Stage();
        stage.setTitle("Ajouter Escale ");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(checkboxAvec, checkboxSans);

// add event listener to toggle group
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (toggleGroup.getSelectedToggle() != null) {
                // get selected radio button text
                String selectedText = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                System.out.println("Selected option: " + selectedText);
            }
        });

        buttonNext.setVisible(false);




        buttonNext.setOnAction(ev->{
            boolean allFieldsFilled = true;
            if(num_volmod.getText().isEmpty() || aero_arrmod.getText().isEmpty() || aero_depmod.getText().isEmpty()
                    || j_volmod.getText() == null || h_arrmod.getText().isEmpty() || h_depmod.getText().isEmpty()) {
                allFieldsFilled = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez remplir tous les champs obligatoires !");
                alert.showAndWait();
            }

            if(allFieldsFilled) {
                try {
                    AjoutEscScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        annmod.setOnAction(event -> {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            annmod.getScene().setRoot(root);
        });

        validmod.setOnAction(event -> {
            if (num_volmod.getText().isEmpty() || aero_arrmod.getText().isEmpty() || aero_depmod.getText().isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs obligatoires");
                alert.setHeaderText("Veuillez remplir tous les champs obligatoires.");
                alert.showAndWait();
            } else {
                try {
                    //Escale w = se.FindById(parseInt(id_esc.getText()));
                    Vol v = new Vol();
                    v.setNum_vol(parseInt(num_volmod.getText()));
                    v.setAero_arrivee(aero_arrmod.getText());
                    v.setAero_depart(aero_depmod.getText());
                    //v.setJour_vol(j_vol.getText());

                    final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    String getDateVol = v.setJour_vol(picker.getValue().format(NEW_FORMATTER).toString());

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");


                    v.setHeure_arrivee(h_arrmod.getText());
                    v.setHeure_depart(h_depmod.getText());
                    //v.setId_avion(3);
                    // v.setEscale(w);
                    if (myEscale!= null) {
                        v.setEscale(myEscale);

                    }else
                    {v.setEscale(null);
                    }
                    System.out.println(v.toString());
                    sv.updateOne(v);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText("Les données ont été validées avec succès !");
                    alert.showAndWait();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("listesc.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                validmod.getScene().setRoot(root);


            }





        });




}
    private static Escale myEscale;
    public void setEscale(Escale esc) {
        myEscale=esc;


    }}
