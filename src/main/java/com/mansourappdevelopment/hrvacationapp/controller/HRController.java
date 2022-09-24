package com.mansourappdevelopment.hrvacationapp.controller;


import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.model.Vacation;
import com.mansourappdevelopment.hrvacationapp.util.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HRController implements Initializable {
    @FXML
    private Label statusLabel;
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
    private TextField employeeUpdatedSickVacationTextField;
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
    @FXML
    private TableColumn<Employee, String> userNameCol;
    @FXML
    private TableColumn<Employee, String> passwordCol;


    //Vacations columns
    @FXML
    private TableView<Vacation> vacationsTable;
    @FXML
    private TableColumn<Vacation, Integer> vacationIdCol;
    @FXML
    private TableColumn<Vacation, Integer> vacationEmployeeIdCol;
    @FXML
    private TableColumn<Vacation, String> vacationEmployeeFirstNameCol;
    @FXML
    private TableColumn<Vacation, String> vacationEmployeeLastNameCol;
    @FXML
    private TableColumn<Vacation, String> vacationTypeCol;
    @FXML
    private TableColumn<Vacation, Integer> vacationDaysCount;
    @FXML
    private TableColumn<Vacation, Button> vacationActions;


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

        vacationIdCol.setCellValueFactory(new PropertyValueFactory<>("vacationId"));
        vacationEmployeeIdCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        vacationEmployeeFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
        vacationEmployeeLastNameCol.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
        vacationTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        vacationDaysCount.setCellValueFactory(new PropertyValueFactory<>("daysCount"));
        addAcceptButton(vacationActions);
    }

    public void createNewEmployee(ActionEvent event) {
        String firstName = employeeFirstNameTextField.getText();
        String lastName = employeeLastNameTextField.getText();
        String userName = employeeUserNameTextField.getText();
        String password = employeePasswordTextField.getText();
        String annualVacationCount = employeeAnnualVacationTextField.getText();
        String sickVacationCount = employeeSickVacationTextField.getText();
        if (Validator.validFirstName(firstName) && Validator.validLastName(lastName) && Validator.validUserName(userName) &&
                Validator.validPassword(password) && Validator.validAnnualVacation(annualVacationCount) &&
                Validator.validSickVacation(sickVacationCount)) {
            hrdao.createNewEmployee(new Employee(null, firstName, lastName, userName, password, Integer.parseInt(annualVacationCount)
                    , Integer.parseInt(sickVacationCount)));
            updateEmployeesTable(hrdao.getAllEmployees());
            statusLabel.setText(firstName + " " + lastName + " Added Successfully");
        } else {
            statusLabel.setText("Check The New Employee Data");
        }
    }

    public void getEmployeeById(ActionEvent event) {
        ArrayList<Employee> emp = null;
        String id = employeeIdTextField.getText();
        if (Validator.validId(id)) {
            emp = new ArrayList<>();
            emp.add(hrdao.getEmployeeById(Integer.parseInt(employeeIdTextField.getText())));
            updateEmployeesTable(emp);
            statusLabel.setText(emp.get(0).getFirstName() + " " + emp.get(0).getLastName() + " Retrieved Successfully");
        } else {
            statusLabel.setText("Enter a Valid Employee ID");
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
        if (Validator.validFirstName(firstName) && Validator.validLastName(lastName) && Validator.validUserName(userName) &&
                Validator.validPassword(password) && Validator.validAnnualVacation(annualVacation) &&
                Validator.validSickVacation(sickVacation)) {
            emp.add(hrdao.updateEmployee(new Employee(Integer.parseInt(id), firstName, lastName, userName, password, Integer.parseInt(annualVacation),
                    Integer.parseInt(sickVacation))));
            updateEmployeesTable(emp);
            statusLabel.setText(firstName + " " + lastName + " Updated Successfully");
        } else {
            statusLabel.setText("Check The New Employee Data");
        }


    }

    public void deleteEmployee(ActionEvent event) {
        String id = employeeDeletedIdTextField.getText();
        if (Validator.validId(id)) {
            hrdao.deleteEmployee(Integer.parseInt(id));
            updateEmployeesTable(hrdao.getAllEmployees());
            statusLabel.setText("Employee Deleted Successfully");
        } else
            statusLabel.setText("Enter a Valid Employee ID");
    }

    public void setHrdao(HRDAO hrdao) {
        this.hrdao = hrdao;
        updateEmployeesTable(hrdao.getAllEmployees());
        updateVacationsTable(hrdao.getAllVacations());
    }

    private void addAcceptButton(TableColumn<Vacation, Button> vacationActions) {
        Callback<TableColumn<Vacation, Button>, TableCell<Vacation, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell call(final TableColumn<Vacation, Button> param) {
                final TableCell<Vacation, Button> cell = new TableCell<Vacation, Button>() {
                    final Button acceptBtn = new Button("Accept");
                    final Button rejectBtn = new Button("Reject");

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            acceptBtn.setOnAction(event -> {
                                Vacation vacation = getTableView().getItems().get(getIndex());
                                hrdao.acceptVacation(vacation);
                                updateVacationsTable(hrdao.getAllVacations());
                                updateEmployeesTable(hrdao.getAllEmployees());
                                statusLabel.setText("Vacation Accepted");
                            });
                            rejectBtn.setOnAction(event -> {
                                Vacation vacation = getTableView().getItems().get(getIndex());
                                hrdao.rejectVacation(vacation.getVacationId());
                                updateVacationsTable(hrdao.getAllVacations());
                                updateEmployeesTable(hrdao.getAllEmployees());
                                statusLabel.setText("Vacation Rejected");
                            });
                            HBox pane = new HBox(acceptBtn, rejectBtn);
                            pane.setSpacing(10);
                            setGraphic(pane);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        vacationActions.setCellFactory(cellFactory);
    }

    private void updateEmployeesTable(List<Employee> employeeList) {
        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
        employeesTable.setItems(employees);
    }

    private void updateVacationsTable(List<Vacation> employeeList) {
        ObservableList<Vacation> vacations = FXCollections.observableArrayList(employeeList);
        vacationsTable.setItems(vacations);
    }
}
