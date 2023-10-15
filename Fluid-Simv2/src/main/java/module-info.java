module com.example.fluidsimv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fluidsimv2 to javafx.fxml;
    exports com.example.fluidsimv2;
}