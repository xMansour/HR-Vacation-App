package com.mansourappdevelopment.hrvacationapp.model;

public class Vacation {
    //0-> Annual, 1-> Sick
    private boolean type;
    private int daysCount;

    public Vacation(boolean type, int daysCount) {
        this.type = type;
        this.daysCount = daysCount;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }
}
