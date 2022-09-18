package com.mansourappdevelopment.hrvacationapp.controller;

import com.mansourappdevelopment.hrvacationapp.dao.EmployeeDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.model.Vacation;
import com.mansourappdevelopment.hrvacationapp.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private Label statusLabel;
    @FXML
    private DatePicker vacationStartDatePicker;
    @FXML
    private DatePicker vacationEndDatePicker;
    @FXML
    private ComboBox<String> vacationTpeComboBox;
    @FXML
    private Label annualVacationLabel;
    @FXML
    private Label sickVacationLabel;
    @FXML
    private Label employeeNameLabel;
    private EmployeeDAO employeeDAO;
    private Employee currentEmployee;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vacationTpeComboBox.getItems().addAll("Annual", "Sick");
        vacationTpeComboBox.setValue("Annual");
    }


    public void setCurrentEmployee(Employee emp) {
        this.currentEmployee = emp;
        updateUi();
    }

    private void updateUi() {
        employeeNameLabel.setText(currentEmployee.getFirstName() + " " + currentEmployee.getLastName());
        annualVacationLabel.setText(String.valueOf(currentEmployee.getAnnualVacationCount()));
        sickVacationLabel.setText(String.valueOf(currentEmployee.getSickVacationCount()));
    }


    @FXML
    private void submitVacation(ActionEvent event) {
        String vacationStartDateDayOfMonth = String.valueOf(vacationStartDatePicker.getValue().getDayOfMonth());
        String vacationStartDateMonth = String.valueOf(vacationStartDatePicker.getValue().getMonth().getValue());
        String vacationStartDateYear = String.valueOf(vacationStartDatePicker.getValue().getYear());

        String vacationEndDateDayOfMonth = String.valueOf(vacationEndDatePicker.getValue().getDayOfMonth());
        String vacationEndDateMonth = String.valueOf(vacationEndDatePicker.getValue().getMonth().getValue());
        String vacationEndDateYear = String.valueOf(vacationEndDatePicker.getValue().getYear());

        String vacationType = vacationTpeComboBox.getValue();
        System.out.println(vacationStartDateDayOfMonth + " " + vacationStartDateMonth + " " + vacationStartDateYear + " " + vacationType);
        System.out.println(vacationEndDateDayOfMonth + " " + vacationEndDateMonth + " " + vacationEndDateYear + " " + vacationType);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = dateFormat.parse(vacationStartDateDayOfMonth + "/" + vacationStartDateMonth + "/" + vacationStartDateYear);
            Date endDate = dateFormat.parse(vacationEndDateDayOfMonth + "/" + vacationEndDateMonth + "/" + vacationEndDateYear);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDate);
            int daysCount = 0;
            while (startCalendar.before(endCalendar)) {
                if ((Calendar.FRIDAY != startCalendar.get(Calendar.DAY_OF_WEEK)) && (Calendar.SATURDAY != startCalendar.get(Calendar.DAY_OF_WEEK)))
                    daysCount++;
                startCalendar.add(Calendar.DATE, 1);
            }
            if (vacationType.equals(Constants.ANNUAL_VACATION)) {
                if (currentEmployee.getAnnualVacationCount() >= daysCount) {
                    currentEmployee.setVacationRequest(new Vacation(Constants.ANNUAL_VACATION, daysCount));
                    employeeDAO.createVacationRequest(currentEmployee);
                    statusLabel.setText("Annual Vacation Request Successfully Made");
                    System.out.println("Annual Request made");
                } else {
                    statusLabel.setText("Requested Days Are More Than The Annual Vacation Days You Have Left");
                    System.out.println("Annual Request can't be made");
                }
            } else {
                if (currentEmployee.getSickVacationCount() >= daysCount) {
                    currentEmployee.setVacationRequest(new Vacation(Constants.SICK_VACATION, daysCount));
                    employeeDAO.createVacationRequest(currentEmployee);
                    statusLabel.setText("Sick Vacation Request Successfully Made");
                    System.out.println("Sick Request made");
                } else {
                    statusLabel.setText("Requested Days Are More Than The Sick Vacation Days You Have Left");
                    System.out.println("Sick Request can't be made");
                }
            }
            System.out.println(daysCount);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
