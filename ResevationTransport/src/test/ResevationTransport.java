/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.CommonController;
;

/**
 *
 * @author firas
 */


    /**
     * @param args the command line arguments
     */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author walid
 */
public class ResevationTransport extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
     Application.launch(args);  
   } 

    @Override
    public void start(Stage stage)  {
        try {  
            CommonController.setSceneContentStartup(stage);
        } catch (IOException ex) {
            Logger.getLogger(ResevationTransport.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
