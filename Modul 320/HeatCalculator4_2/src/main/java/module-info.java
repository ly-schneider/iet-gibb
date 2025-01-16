module ch.iet_gibb.heatcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.iet_gibb to javafx.fxml;
    exports ch.iet_gibb;
}