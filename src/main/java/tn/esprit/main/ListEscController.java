package tn.esprit.main;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.jdbc.entities.Escale;
import tn.esprit.jdbc.services.ServiceEscale;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListEscController implements Initializable {
    @FXML
    private TableColumn<Escale, String> aeroesc;


    @FXML
    private TableColumn<Escale, String> haesc;

    @FXML
    private TableColumn<Escale, String> hdesc;

    @FXML
    private TableColumn<Escale, Void> jesc;




    @FXML
    private TableView<Escale> tbesc;
    @FXML
    private TableColumn<Escale, Void> actionsColumn;
    @FXML
    private Button logout_button;
    @FXML
    private Button fetch;
    @FXML
    private Button chauffeur;
    @FXML
    private Button gavion;
    @FXML
    private Button gsiege;
    @FXML
    private Button reclamation;
    @FXML
    private Button dvol;
    @FXML
    private Button gvol;
    @FXML
    private Button gmoy;

    ServiceEscale sesc = new ServiceEscale();
    private void refreshTable() {
        try {
            List<Escale> esc = sesc.selectAll();
            ObservableList<Escale> observableArrayList = FXCollections.observableArrayList(sesc.selectAll());
            tbesc.setItems(observableArrayList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetch.setOnAction(f->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fetch.getScene().setRoot(root);
        });
        chauffeur.setOnAction(ch->{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("adminChauffeurDash.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            chauffeur.getScene().setRoot(root);

        });

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
        reclamation.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("addReponse.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gavion.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouter2.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gsiege.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterS.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gmoy.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererM.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        dvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        gvol.setOnAction(logout ->  {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("listesVol.fxml"));
            Parent root= null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logout_button.getScene().setRoot(root);
        } );
        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





        actionsColumn.setCellFactory(column -> {
            return new TableCell<Escale, Void>() {
                private final Button deletebt = new Button("Supprimer");
                private final Button modify = new Button("Modifier");

                {
                    deletebt.setStyle("-fx-cursor: hand;");
                    modify.setStyle("-fx-cursor: hand;");

                    deletebt.setOnAction((event) -> {
                        try {
                            Escale escale = getTableView().getItems().get(getIndex());
                            sesc.deleteOne(escale.getId_escale());
                            refreshTable();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });


                    modify.setOnAction((event) -> {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifesc.fxml"));
                        Parent root= null;
                        try {
                            root = loader.load();
                            ModifyEscController mvc=loader.getController();
                            mvc.setEscale(getTableView().getItems().get(getIndex()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        modify.getScene().setRoot(root);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setGraphic(new javafx.scene.layout.HBox(deletebt, modify));
                    } else {
                        setGraphic(null);
                    }
                }
            };
        });}

    public void load() throws SQLException {
        List<Escale> escale = sesc.selectAll();
        System.out.println(escale.toString());
        ObservableList<Escale> esclist= FXCollections.observableArrayList(escale);
        aeroesc.setCellValueFactory(new PropertyValueFactory<>("aero_escale"));
        hdesc.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
        haesc.setCellValueFactory(new PropertyValueFactory<>("heure_arrivee"));
        jesc.setCellValueFactory(new PropertyValueFactory<>("jour_escale"));
        tbesc.setItems(esclist);

    }
}
