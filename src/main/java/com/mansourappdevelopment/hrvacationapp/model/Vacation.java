package com.mansourappdevelopment.hrvacationapp.model;

import com.mansourappdevelopment.hrvacationapp.util.DataTransferObject;

public class Vacation implements DataTransferObject {
    private Integer vacationId;
    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String type;
    private int daysCount;

    public Vacation(String type, int daysCount) {
        this.type = type;
        this.daysCount = daysCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public Integer getVacationId() {
        return vacationId;
    }

    public void setVacationId(Integer vacationId) {
        this.vacationId = vacationId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "vacationId=" + vacationId +
                ", employeeId=" + employeeId +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", type='" + type + '\'' +
                ", daysCount=" + daysCount +
                '}';
    }

    @Override
    public int getId() {
        return 0;
    }
}
