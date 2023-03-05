package tn.esprit.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.entities.ReservationTransport;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.services.ReservationTservice;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ChauffeurDashController implements Initializable {
    @FXML
    private TableView<ReservationTransport> reservations;
    @FXML
    private TableColumn<ReservationTransport,String> AjouterCol;

    @FXML
    private TableColumn<ReservationTransport,String> DebutCol;

    @FXML
    private TableColumn<ReservationTransport,String> FinCol;

    @FXML
    private TableColumn<MoyenTransport,String> MoyenCol;

    @FXML
    private TableColumn<ReservationTransport,String> destinCol;

    @FXML
    private Button reservation;
    @FXML
    private Button mission;
    @FXML
    private Button fetch;
    @FXML
    private Button logout_button;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button search_button;

    @FXML
    private TextField search_field;
    ReservationTservice rt =new ReservationTservice();
    static User user;

    public void setChauffeur(User u) {
        user=u;
    }
    private void refreshTable() {

        ObservableList<ReservationTransport> observableArrayList = FXCollections.observableArrayList(rt.getAvailableReservation());
        reservations.setItems(observableArrayList);
    }

    private void load( List<ReservationTransport> res) throws SQLException {

        ObservableList<ReservationTransport> observableArrayList = FXCollections.observableArrayList(res);
        destinCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        DebutCol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        FinCol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        MoyenCol.setCellValueFactory(new PropertyValueFactory<>("id_moyent"));


        AjouterCol.setCellFactory(column -> {
            TableCell<ReservationTransport, String> cell = new TableCell<>() {
                private final Button addBtn = new Button("Ajouter");
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(addBtn);
                    }
                    addBtn.setOnMouseClicked((MouseEvent event) -> {
                        try {
                            ReservationTransport res=getTableView().getItems().get(getIndex());
                            rt.addChauffeurTo(user.getId(),res.getId_reservationt());
                            System.out.println(res.getId_reservationt());
                            refreshTable();

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    });
                }
            };
            return cell;
        });
        reservations.setItems(observableArrayList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            load(rt.getAvailableReservation());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mission.setOnAction(l->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("chauffeurReservation.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ChauffeurReservationController crc=loader.getController();
            crc.setChauffeur(user);
            mission.getScene().setRoot(root);
        });
        logout_button.setOnAction(l->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        });
        fetch.setOnAction(l->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("chauffeur.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ChauffeurController cc=loader.getController();
            try {
                cc.setChauffeur(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            logout_button.getScene().setRoot(root);
        });
        reservation.setOnAction(l->{
           refreshTable();

        });
        search_field.setOnKeyTyped(recherche -> {
            try {


                load(rt.rechercheBy(search_field.getText()));
                if (search_field.getText().isEmpty())
                    refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }


}
