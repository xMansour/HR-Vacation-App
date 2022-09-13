package com.mansourappdevelopment.hrvacationapp.util;

import com.mansourappdevelopment.hrvacationapp.model.Employee;

import java.sql.Connection;
import java.util.List;

public abstract class HRDataAccessObject<T extends DataTransferObject> {
    protected final Connection connection;

    public HRDataAccessObject(Connection connection) {
        this.connection = connection;
    }

    public abstract Employee findEmployeeById(int id);

    public abstract List<T> getAllEmployees();

    public abstract void createNewEmployee(T dto);

    public abstract void updateEmployee(T dto);

    public abstract void deleteEmployee(long id);
}
