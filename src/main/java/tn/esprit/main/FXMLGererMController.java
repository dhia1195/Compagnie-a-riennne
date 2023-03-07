/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.main;

import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.services.MoyenTransportService;
import tn.esprit.jdbc.utils.CommonController;
import tn.esprit.jdbc.utils.Context;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static tn.esprit.jdbc.utils.CommonController.setSceneContent;

/**
 * FXML Controller class
 *
 * @author azizi
 */
public class FXMLGererMController extends CommonController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnMettreajour;

    private TableColumn colonneville;
    @FXML
    private TableColumn PrixColone;
    private TableColumn colonneidPROMOTION;
    @FXML
    private TableColumn colonnedescription;
    @FXML
    private TableColumn colonnetype;
    @FXML
    private TableView<MoyenTransport> TableMoyenTransport;
    private MoyenTransportService MoyenTservice = new MoyenTransportService();

    private TableColumn colonnenom_v;
    @FXML
    private TableColumn<?, ?> colonnemodele;
    @FXML
    private TableColumn<?, ?> colonnefrais;
    @FXML
    private TableColumn<?, ?> colonnephoto;
    @FXML
    private Button btnAdf;
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
    private Button gmoy;
    @FXML
    private Button btnSMS;
    @FXML
    private Button dvol;
    @FXML
    private Button gvol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        colonnemodele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        colonnetype.setCellValueFactory(new PropertyValueFactory<>("type"));
        PrixColone.setCellValueFactory(new PropertyValueFactory<>("prix_location_jour"));
       colonnefrais.setCellValueFactory(new PropertyValueFactory<>("frais_chauffeur"));
        colonnedescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colonnephoto.setCellValueFactory(new PropertyValueFactory<>("photo"));
        
        // récupère les données des utilisateurs depuis la base de données
        List<MoyenTransport> vleList = MoyenTservice.afficheListe();

        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vleList);

//        // Ajoute un listener sur le tableau pour détecter quand une ligne est sélectionnée
//        TableMoyenTransport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MoyenTransport>() {
//            @Override
//            public void changed(ObservableValue<? extends MoyenTransport> obs, MoyenTransport oldSelection, MoyenTransport newSelection) {
//                if (newSelection != null) {
//                    // Récupère le MoyenTransport sélectionné
//                    MoyenTransport MoyenTransportSelectionne = TableMoyenTransport.getSelectionModel().getSelectedItem();
//                    // Charge le fichier FXML de la nouvelle interface
//                    FXMLLoader loader = new FXMLLoader(FXMLGererMoyenTransportController.this.getClass().getResource("FXMLLouerV.fxml"));
//                    Parent root;
//                    try {
//                        root = loader.load();
//                    } catch (IOException ex) {
//                        Logger.getLogger(FXMLGererMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
//                        return;
//                    }   // Obtient le contrôleur de la nouvelle interface
//                    FXMLLouerVController controller = loader.getController();
//                    // Configure le contrôleur avec les données du MoyenTransport sélectionné
//                    controller.initialiserAvecMoyenTransport(MoyenTransportSelectionne);
//                    // Charge la nouvelle interface dans une nouvelle fenêtre
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.show();
//                }
//            }
//        }}
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        /*   MoyenTransportService sp=new MoyenTransportService();
         
           String id_MoyenTransport = txtid_MoyenTransport.getText();
       int id = Integer  .parseInt(txtid.getText());
           String photo = txtphoto.getText();
           String ville = txtville.getText();
        float prix = Float.parseFloat(txtprix.getText());
        int id_promotion = Integer  .parseInt(txtid_promotion.getText());
        String description = txtdescription.getText();
        String type = txttype.getText();
         
        
   MoyenTransport a = new MoyenTransport(id_MoyenTransport,nom_v ,  id,  photo,  ville,  prix,  id_promotion,  description,  type);
   sp.ajouter(a);
         
     


   List<MoyenTransport> vList = MoyenTransportService.afficheListe();
        
        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vList);*/

        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAjouterMoyenTransport.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fetch.getScene().setRoot(root);

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        MoyenTransport selectedMoyenTransport = TableMoyenTransport.getSelectionModel().getSelectedItem();

        if (selectedMoyenTransport == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No car selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a car in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected car?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(selectedMoyenTransport);
            MoyenTservice.supprimer(selectedMoyenTransport);
        List<MoyenTransport> vleList = MoyenTservice.afficheListe();

            // affiche les données dans le tableau
            TableMoyenTransport.getItems().setAll(vleList);
        }
    }

    @FXML
    private void Metrreajour(ActionEvent event) {
        /*                    String id_MoyenTransport = txtid_MoyenTransport.getText();
           int id = Integer  .parseInt(txtid.getText());
           String photo = txtphoto.getText();
           String ville = txtville.getText();
        float prix = Float.parseFloat(txtprix.getText());
        int id_promotion = Integer  .parseInt(txtid_promotion.getText());
        String description = txtdescription.getText();
        String type = txttype.getText();
         
        MoyenTransportService sp=new MoyenTransportService();
  MoyenTransport a = new MoyenTransport(id_MoyenTransport,  id,  photo,  ville,  prix,  id_promotion,  description,  type);
   sp.modifier(a);
 List<MoyenTransport> vList = MoyenTransportService.afficheListe();
        
        // affiche les données dans le tableau
        TableMoyenTransport.getItems().setAll(vList);}*/
        MoyenTransport selectedMoyenTransport = TableMoyenTransport.getSelectionModel().getSelectedItem();

        Context.getInstance().addContextObject("moyentransport", selectedMoyenTransport);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLModifiermoyentransport.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnAjouter.getScene().setRoot(root);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        MoyenTransport v = TableMoyenTransport.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException, IOException   {
        
        
    long millis = System.currentTimeMillis();
    java.sql.Date DateRapport = new java.sql.Date(millis);

    String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);
    System.out.println("Date d'aujourdhui : " + DateLyoum);

    com.itextpdf.text.Document document = new com.itextpdf.text.Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));
        document.open();
// Ajouter le logo
       /* Image logo = Image.getInstance("C://xampp//htdocs//onlywork/logo.png");
        logo.scaleAbsolute(100, 100);
        logo.setAlignment(Element.ALIGN_CENTER);
        document.add(logo);*/
        // Ajouter un titre avec un style personnalisé
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
        Paragraph title = new Paragraph("Rapport détaillé de notre application", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Ajouter un paragraphe avec un style personnalisé
        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
        Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les moyens de transports. Pour chaque moyen, nous fournissons des informations telles que la date d'aujourd'hui : " + DateRapport, paragraphFont);
        ph1.setSpacingAfter(10);
        document.add(ph1);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        // Créer une cellule avec un style personnalisé
        Font cellFont = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase("Model", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("type", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("prix par jour", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("frais chauffeur", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
                
        cell = new PdfPCell(new Phrase("description", cellFont));
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
            
        MoyenTransportService r = new MoyenTransportService();
        r.afficheListe().forEach(e -> {
            table.addCell(String.valueOf(e.getModele()));
            table.addCell(String.valueOf(e.getType()));
            table.addCell(String.valueOf(e.getPrix_location_jour()));
            table.addCell(String.valueOf(e.getFrais_chauffeur()));
            table.addCell(String.valueOf(e.getDescription()));

        });
                

        document.add(table);
    } catch (Exception e) {
        System.out.println(e);
    }
    document.close();

    // Ouvrir le fichier PDF
    File file = new File(DateLyoum + ".pdf");
    Desktop desktop = Desktop.getDesktop();
    if (file.exists()) {
        desktop.open(file);
    }
    }

    @FXML
    private void SMS(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("messaget.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        btnAjouter.getScene().setRoot(root);
    }
}
