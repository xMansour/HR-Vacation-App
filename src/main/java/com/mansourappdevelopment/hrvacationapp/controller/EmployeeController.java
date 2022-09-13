package com.mansourappdevelopment.hrvacationapp.controller;

import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import com.mansourappdevelopment.hrvacationapp.util.Validator;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager("config.properties");
        Connection connection = dbConnectionManager.getConnection();
        if (!Validator.tableExists(connection)) {
            DBManager dbManager = new DBManager(connection);
            dbManager.setUpDatabase();
        }
    }
}
