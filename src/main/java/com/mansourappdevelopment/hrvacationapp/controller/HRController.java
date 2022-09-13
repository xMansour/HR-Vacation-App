package com.mansourappdevelopment.hrvacationapp.controller;


import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
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
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, Integer> idCol;
    @FXML
    private TableColumn<Employee, String> firstNameCol;
    @FXML
    private TableColumn<Employee, String> lastNameCol;
    @FXML
    private TableColumn<Employee, Integer> annualVacationCountCol;
    @FXML
    private TableColumn<Employee, Integer> sickVacationCountCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        annualVacationCountCol.setCellValueFactory(new PropertyValueFactory<>("annualVacationCount"));
        sickVacationCountCol.setCellValueFactory(new PropertyValueFactory<>("sickVacationCount"));

        DBConnectionManager dbConnectionManager = new DBConnectionManager("config.properties");
        Connection connection = dbConnectionManager.getConnection();
        DBManager dbManager = new DBManager(connection);
        dbManager.setUpDatabase();
        HRDAO hrdao = new HRDAO(connection);
        ObservableList<Employee> employees = FXCollections.observableArrayList(hrdao.getAllEmployees());
        employees.forEach(System.out::println);
        employeesTable.setItems(employees);

    }
}
