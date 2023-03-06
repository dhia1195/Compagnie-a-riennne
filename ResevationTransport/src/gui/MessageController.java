/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static utils.CommonController.setSceneContent;

public class MessageController {
    // Replace with your Twilio Account SID and Auth Token
    public static final String ACCOUNT_SID = "AC81de80a79414f9512ef50709133aa65e";
    public static final String AUTH_TOKEN = "f3dfec463b9a8617e0e43e7de735e760";
    public static final String TWILIO_NUMBER = "+15674122481";

    private Label statusLabel;

    @FXML
    private TextField textfield;
    @FXML
    private Button btnBack;

    @FXML
    private void sendSMS() {
    String toPhoneNumber = textfield.getText();
    if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
        statusLabel.setText("Please enter a phone number.");
        return;
    }

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "Est ce que c'est confirmee la reservation d'un moyen du transport? " + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();

    if (message.getSid() != null) {
        statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    } else {
        statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    }
}

    @FXML
    private void back(ActionEvent event) {
         try {
            setSceneContent("FXMLGererM");
        } catch (IOException ex) {
            Logger.getLogger(FXMLReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
