/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author walid
 */
public abstract class CommonController implements Initializable{
public final Logger LOGGER = Logger.getLogger(this.getClass().getName());  
   private static final String UI_BASE_LOCATION = "/gui/";  
   private static final String FXML_PREFIX = ".fxml";  
   public static void setSceneContentStartup(Stage stage) throws IOException{  
     Context.getInstance().setCurrentStage(stage);  
     setSceneContent("FXMLLouerVehicule");  
   }  
   public static Parent setSceneContent(String pageName) throws IOException {  
     Stage currentStage = Context.getInstance().getCurrentStage();  
       Scene scene = currentStage.getScene();  
     Parent page = (Parent) FXMLLoader.load(CommonController.class.getResource(UI_BASE_LOCATION + pageName + FXML_PREFIX));  
     if (scene == null) {  
       scene = new Scene(page);  
       currentStage.setScene(scene);  
       currentStage.setTitle("Sample JavaFX Application");  
       currentStage.setWidth(800);  
       currentStage.setHeight(600);  
     } else {  
       currentStage.getScene().setRoot(page);  
     }  
     currentStage.sizeToScene();  
     currentStage.show();  
     return page;  
   }  
 }
   
