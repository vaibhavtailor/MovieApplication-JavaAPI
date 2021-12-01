module com.example.api {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.example.api to javafx.fxml;
    exports com.example.api;
}