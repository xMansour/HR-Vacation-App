package com.mansourappdevelopment.hrvacationapp.model;

public class HR extends User {
    private boolean isHr;

    public HR(Integer id, String firstName, String lastName, String userName, String password, boolean isHr) {
        super(id, firstName, lastName, userName, password);
        this.isHr = isHr;
    }

    public boolean isHr() {
        return isHr;
    }

    public void setHr(boolean hr) {
        isHr = hr;
    }

}
