module com.example.pi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.mail.api;
    requires org.jfree.jfreechart;
    requires itextpdf;
    requires java.desktop;


    opens tn.esprit.main to javafx.fxml;
    opens tn.esprit.jdbc.entities to javafx.base;
    exports tn.esprit.main;
}