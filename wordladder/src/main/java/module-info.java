module com.henryofskalitz {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.henryofskalitz to javafx.fxml;
    exports com.henryofskalitz;
}
