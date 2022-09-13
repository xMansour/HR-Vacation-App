package com.mansourappdevelopment.hrvacationapp.controller;


import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class HRController implements Initializable {
    @FXML
    private TextField employeeIdTextField;
    @FXML
    private Button getEmployeeBtn;
    @FXML
    private TextField employeeFirstNameTextFiled;
    @FXML
    private TextField employeeLastNameTextFiled;
    @FXML
    private TextField employeeAnnualVacationTextFiled;
    @FXML
    private TextField employeeSickVacationTextFiled;
    @FXML
    private Button createEmployeeBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager("config.properties");
        Connection connection = dbConnectionManager.getConnection();
        DBManager dbManager = new DBManager(connection);
        dbManager.setUpDatabase();
        HRDAO hrdao = new HRDAO(connection);
        hrdao.getAllEmployees().forEach(System.out::println);

    }
}
