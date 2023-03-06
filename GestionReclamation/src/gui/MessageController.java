package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MessageController {
    // Replace with your Twilio Account SID and Auth Token
    public static final String ACCOUNT_SID = "AC83dd4c4d27fe7ffd13479195831da39b";
    public static final String AUTH_TOKEN = "2d53b334a6f94b4d841f0eead683cee3";
    public static final String TWILIO_NUMBER = "+15673131181";

    @FXML
    private Label statusLabel;

    @FXML
    private TextField textfield;

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
    String messageText = "La réclamation du Client Foulen Ben Foulen a été éditée le " + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();

    if (message.getSid() != null) {
        statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    } else {
        statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    }
}

}
