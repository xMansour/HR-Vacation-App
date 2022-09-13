package com.mansourappdevelopment.hrvacationapp.controller;


import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import com.mansourappdevelopment.hrvacationapp.util.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableColumn userNameCol;
    @FXML
    private TableColumn passwordCol;
    @FXML
    private TextField employeePasswordTextField;
    @FXML
    private TextField employeeUserNameTextField;
    @FXML
    private TextField employeeDeletedIdTextField;
    @FXML
    private TextField employeeUpdatedPasswordTextField;
    @FXML
    private TextField employeeUpdatedUserNameTextField;
    @FXML
    private TextField employeeUpdatedIdTextField;
    @FXML
    private TextField employeeUpdatedFirstNameTextField;
    @FXML
    private TextField employeeUpdatedLastNameTextField;
    @FXML
    private TextField employeeUpdatedAnnualVacationTextField;
    @FXML
    public TextField employeeUpdatedSickVacationTextField;
    @FXML
    private TextField employeeIdTextField;
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
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        annualVacationCountCol.setCellValueFactory(new PropertyValueFactory<>("annualVacationCount"));
        sickVacationCountCol.setCellValueFactory(new PropertyValueFactory<>("sickVacationCount"));

        DBConnectionManager dbConnectionManager = new DBConnectionManager("config.properties");
        Connection connection = dbConnectionManager.getConnection();
        if (!Validator.tableExists(connection)) {
            DBManager dbManager = new DBManager(connection);
            dbManager.setUpDatabase();
        }
        hrdao = new HRDAO(connection);
        updateEmployeesTable(hrdao.getAllEmployees());
    }

    public void createNewEmployee(ActionEvent event) {
        String firstName = employeeFirstNameTextField.getText();
        String lastName = employeeLastNameTextField.getText();
        String userName = employeeUserNameTextField.getText();
        String password = employeePasswordTextField.getText();
        int annualVacationCount = Integer.parseInt(employeeAnnualVacationTextField.getText());
        int sickVacationCount = Integer.parseInt(employeeSickVacationTextField.getText());
        hrdao.createNewEmployee(new Employee(null, firstName, lastName, userName, password, annualVacationCount, sickVacationCount));
        updateEmployeesTable(hrdao.getAllEmployees());
    }

    private void updateEmployeesTable(List<Employee> employeeList) {
        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
        employeesTable.setItems(employees);
    }

    public void getEmployeeById(ActionEvent event) {
        ArrayList<Employee> emp = null;
        String id = employeeIdTextField.getText();
        if (Validator.validId(id)) {
            emp = new ArrayList<>();
            emp.add(hrdao.getEmployeeById(Integer.parseInt(employeeIdTextField.getText())));
            updateEmployeesTable(emp);
        } else {
            updateEmployeesTable(hrdao.getAllEmployees());
        }
    }

    public void updateEmployee(ActionEvent event) {
        String id = employeeUpdatedIdTextField.getText();
        String firstName = employeeUpdatedFirstNameTextField.getText();
        String lastName = employeeUpdatedLastNameTextField.getText();
        String userName = employeeUpdatedUserNameTextField.getText();
        String password = employeeUpdatedPasswordTextField.getText();
        String annualVacation = employeeUpdatedAnnualVacationTextField.getText();
        String sickVacation = employeeUpdatedSickVacationTextField.getText();

        ArrayList<Employee> emp = new ArrayList<>();
        emp.add(hrdao.updateEmployee(new Employee(Integer.parseInt(id), firstName, lastName, userName, password, Integer.parseInt(annualVacation),
                Integer.parseInt(sickVacation))));
        updateEmployeesTable(emp);

    }

    public void deleteEmployee(ActionEvent event) {
        String id = employeeDeletedIdTextField.getText();
        if (Validator.validId(id)) {
            hrdao.deleteEmployee(Integer.parseInt(id));
            updateEmployeesTable(hrdao.getAllEmployees());
        }
    }
}
