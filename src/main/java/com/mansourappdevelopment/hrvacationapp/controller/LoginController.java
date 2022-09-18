package com.mansourappdevelopment.hrvacationapp.controller;

import com.mansourappdevelopment.hrvacationapp.dao.EmployeeDAO;
import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import com.mansourappdevelopment.hrvacationapp.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class LoginController {
    @FXML
    private Label statusLabel;
    @FXML
    private PasswordField passwordInputField;
    @FXML
    private TextField userNameInputFiled;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        String userName = userNameInputFiled.getText();
        String password = passwordInputField.getText();
        if (Validator.validUserName(userName)) {
            DBConnectionManager dbConnectionManager = new DBConnectionManager("config.properties");
            Connection connection = dbConnectionManager.getConnection();
            HRDAO hrdao = new HRDAO(connection);
            DBManager dbManager = new DBManager(connection);
            if (!dbManager.employeesTableExists(connection)) {
                dbManager.setUpEmployeesDatabases();
            }
            if (!dbManager.vacationsTableExists(connection)) {
                dbManager.setUpVacationsDatabases();
            }
            if (userName.equals("hr")) {
                if (Validator.validPassword(password) && password.equals("hr")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mansourappdevelopment/hrvacationapp/hr-view.fxml"));
                    Parent root = fxmlLoader.load();
                    HRController hrController = fxmlLoader.getController();
                    hrController.setHrdao(hrdao);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("HR Login Successful");
                } else {
                    statusLabel.setText("Wrong Password");
                }
            } else {
                Employee emp = hrdao.getEmployeeByUserName(userName);
                if (emp != null) {
                    if (password.equals(emp.getPassword())) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mansourappdevelopment/hrvacationapp/employee-view.fxml"));
                        Parent root = fxmlLoader.load();
                        EmployeeController employeeController = fxmlLoader.getController();
                        EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                        employeeController.setEmployeeDAO(employeeDAO);
                        employeeController.setCurrentEmployee(emp);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        System.out.println("Employee Login Successful");
                    } else {
                        statusLabel.setText("Enter A Valid Employee ID");
                        System.out.println("Wrong password");
                    }
                } else {
                    statusLabel.setText("No Such Employee With Username: " + userName);
                    System.out.println("Wrong username");
                }


            }
        }

    }
}