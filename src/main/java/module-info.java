module com.mansourappdevelopment.hrvacationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;

    opens com.mansourappdevelopment.hrvacationapp to javafx.fxml;
    exports com.mansourappdevelopment.hrvacationapp;
    exports com.mansourappdevelopment.hrvacationapp.controller;
    opens com.mansourappdevelopment.hrvacationapp.controller to javafx.fxml;
}