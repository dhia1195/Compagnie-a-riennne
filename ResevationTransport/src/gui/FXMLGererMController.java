/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.MoyenTransport;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.MoyenTransportService;
import utils.CommonController;
import utils.Context;

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
    private Button btnSMS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/FXMLAjouterMoyenTransport.fxml"));

            Scene sc = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAjouterMoyenTransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            setSceneContent("FXMLModifiermoyentransport");
        } catch (IOException ex) {
            Logger.getLogger(FXMLModifiermoyentransportController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les événements. Pour chaque événement, nous fournissons des informations telles que la date d'aujourd'hui : " + DateRapport, paragraphFont);
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
             try {
            setSceneContent("message");
        } catch (IOException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
