module com.example.pi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.mail.api;


    opens tn.esprit.main to javafx.fxml;
    opens tn.esprit.jdbc.entities to javafx.base;
    exports tn.esprit.main;
}