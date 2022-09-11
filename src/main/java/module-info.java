module com.mansourappdevelopment.hrvacationapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.mansourappdevelopment.hrvacationapp to javafx.fxml;
    exports com.mansourappdevelopment.hrvacationapp;
}