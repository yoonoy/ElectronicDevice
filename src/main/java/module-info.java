module com.example.device {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.device to javafx.fxml;
    exports com.example.device;
}