package com.mansourappdevelopment.hrvacationapp.util;

import com.mansourappdevelopment.hrvacationapp.model.Employee;

import java.sql.Connection;

public abstract class EmployeeDataAccessObject<T extends DataTransferObject> {
    protected final Connection connection;

    public EmployeeDataAccessObject(Connection connection) {
        this.connection = connection;
    }

    public abstract void createVacationRequest(Employee dto);

}
