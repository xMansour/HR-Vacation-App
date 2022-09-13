module com.mansourappdevelopment.hrvacationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    exports com.mansourappdevelopment.hrvacationapp;
    exports com.mansourappdevelopment.hrvacationapp.controller;
    exports com.mansourappdevelopment.hrvacationapp.model;
    exports com.mansourappdevelopment.hrvacationapp.dao;
    opens com.mansourappdevelopment.hrvacationapp to javafx.fxml;
    opens com.mansourappdevelopment.hrvacationapp.model to javafx.base;

    opens com.mansourappdevelopment.hrvacationapp.controller to javafx.fxml;
}