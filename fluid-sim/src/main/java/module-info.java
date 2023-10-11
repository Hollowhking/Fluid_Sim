module com.example.fluidsim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fluidsim to javafx.fxml;
    exports com.example.fluidsim;
}