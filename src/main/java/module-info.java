module com.example.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.sample to javafx.fxml;
    exports com.example.sample;
}