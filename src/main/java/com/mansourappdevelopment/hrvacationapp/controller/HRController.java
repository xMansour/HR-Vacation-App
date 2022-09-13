package com.mansourappdevelopment.hrvacationapp.controller;


import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HRController implements Initializable {
    @FXML
    private TextField employeeIdTextField;
    @FXML
    private Button getEmployeeBtn;
    @FXML
    private TextField employeeFirstNameTextField;
    @FXML
    private TextField employeeLastNameTextField;
    @FXML
    private TextField employeeAnnualVacationTextField;
    @FXML
    private TextField employeeSickVacationTextField;

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

    private HRDAO hrdao;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        annualVacationCountCol.setCellValueFactory(new PropertyValueFactory<>("annualVacationCount"));
        sickVacationCountCol.setCellValueFactory(new PropertyValueFactory<>("sickVacationCount"));

        DBConnectionManager dbConnectionManager = new DBConnectionManager("config.properties");
        Connection connection = dbConnectionManager.getConnection();
        if (!tableExists(connection)) {
            DBManager dbManager = new DBManager(connection);
            dbManager.setUpDatabase();
        }
        hrdao = new HRDAO(connection);
        updateEmployeesTable(hrdao.getAllEmployees());

    }

    public void createNewEmployee(ActionEvent event) {
        String firstName = employeeFirstNameTextField.getText();
        String lastName = employeeLastNameTextField.getText();
        int annualVacationCount = Integer.parseInt(employeeAnnualVacationTextField.getText());
        int sickVacationCount = Integer.parseInt(employeeSickVacationTextField.getText());
        hrdao.createNewEmployee(new Employee(null, firstName, lastName, annualVacationCount, sickVacationCount));
        updateEmployeesTable(hrdao.getAllEmployees());
    }

    private void updateEmployeesTable(List<Employee> employeeList) {
        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
        employeesTable.setItems(employees);
    }

    private boolean tableExists(Connection connection) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "EMPLOYEES",
                    new String[]{"TABLE"});
            resultSet.next();
            if (resultSet.getString("TABLE_NAME").equals("EMPLOYEES"))
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void getEmployeeById(ActionEvent event) {
        ArrayList<Employee> emp = null;
        if (employeeIdTextField.getText().equals("")) {
            updateEmployeesTable(hrdao.getAllEmployees());
        } else {
            emp = new ArrayList<>();
            emp.add(hrdao.findEmployeeById(Integer.parseInt(employeeIdTextField.getText())));
            updateEmployeesTable(emp);
        }
    }
}
