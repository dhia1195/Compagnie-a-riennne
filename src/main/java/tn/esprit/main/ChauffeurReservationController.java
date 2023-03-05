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
import java.util.ResourceBundle;




public class ChauffeurReservationController implements Initializable {
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
    private Button fetch;
    @FXML
    private Button reservation;

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
    static int id_u;


    public void setChauffeur(User u) {
        user=u;
        id_u=u.getId();
    }


    private void refreshTable() {
        ObservableList<ReservationTransport> observableArrayList = FXCollections.observableArrayList(rt.getByIdChauffeur(id_u));
        reservations.setItems(observableArrayList);
    }
    private void load() throws SQLException {

        ObservableList<ReservationTransport> observableArrayList = FXCollections.observableArrayList(rt.getByIdChauffeur(id_u));
        destinCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        DebutCol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        FinCol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        MoyenCol.setCellValueFactory(new PropertyValueFactory<>("id_moyent"));


        AjouterCol.setCellFactory(column -> {
            TableCell<ReservationTransport, String> cell = new TableCell<>() {
                private final Button supprimBtn = new Button("Supprimer");
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(supprimBtn);
                    }
                    supprimBtn.setOnMouseClicked((MouseEvent event) -> {
                        try {
                            ReservationTransport res=getTableView().getItems().get(getIndex());
                            rt.addChauffeurTo(null,res.getId_reservationt());
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
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reservation.setOnAction(l->{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("chauffeurDash.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            reservation.getScene().setRoot(root);
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


    }


}
