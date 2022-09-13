package com.mansourappdevelopment.hrvacationapp.model;

public class HR extends User {
    private boolean isHr;

    public HR(Integer id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public boolean isHr() {
        return isHr;
    }

    public void setHr(boolean hr) {
        isHr = hr;
    }

}
