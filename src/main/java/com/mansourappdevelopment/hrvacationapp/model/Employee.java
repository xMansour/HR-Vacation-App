package com.mansourappdevelopment.hrvacationapp.model;

public class Employee extends User {
    private int annualVacationCount;
    private int sickVacationCount;
    private Vacation vacationRequest;

    public Employee(Integer id, String firstName, String lastName, int annualVacationCount, int sickVacationCount) {
        super(id, firstName, lastName);
        this.annualVacationCount = annualVacationCount;
        this.sickVacationCount = sickVacationCount;
    }

    public int getAnnualVacationCount() {
        return annualVacationCount;
    }

    public void setAnnualVacationCount(int annualVacationCount) {
        this.annualVacationCount = annualVacationCount;
    }

    public int getSickVacationCount() {
        return sickVacationCount;
    }

    public void setSickVacationCount(int sickVacationCount) {
        this.sickVacationCount = sickVacationCount;
    }

    public Vacation getVacationRequest() {
        return vacationRequest;
    }

    public void setVacationRequest(Vacation vacationRequest) {
        this.vacationRequest = vacationRequest;
    }
}
