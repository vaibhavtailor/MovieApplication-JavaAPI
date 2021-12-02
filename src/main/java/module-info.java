module com.example.api {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.example.api to javafx.fxml, com.google.gson;
    exports com.example.api;
    exports com.example.api.Controllers;
    opens com.example.api.Controllers to com.google.gson, javafx.fxml;
}