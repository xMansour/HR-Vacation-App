package com.mansourappdevelopment.hrvacationapp.controller;

import com.mansourappdevelopment.hrvacationapp.dao.HRDAO;
import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.DBConnectionManager;
import com.mansourappdevelopment.hrvacationapp.util.DBManager;
import com.mansourappdevelopment.hrvacationapp.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
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
    private HRDAO hrdao;
    private Employee currentEmployee;
    private Date endDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vacationTpeComboBox.getItems().addAll("Annual", "Sick");
        vacationTpeComboBox.setValue("Annual");
    }

    public void setHrdao(HRDAO hrdao) {
        this.hrdao = hrdao;
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
            if (vacationType.equals("Annual")) {
                if (currentEmployee.getAnnualVacationCount() >= daysCount) {
                    //TODO:: create a vacation request
                    System.out.println("Annual Request made");
                } else {
                    //TODO:: Days off are more than the available days count
                    System.out.println("Annual Request can't be made");
                }
            } else {
                if (currentEmployee.getSickVacationCount() >= daysCount) {
                    //TODO:: create a vacation request
                    System.out.println("Sick Request made");
                } else {
                    //TODO:: Days off are more than the available days count
                    System.out.println("Sick Request can't be made");
                }
            }
            System.out.println(daysCount);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
