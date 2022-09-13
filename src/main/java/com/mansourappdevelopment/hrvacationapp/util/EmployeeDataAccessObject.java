package com.mansourappdevelopment.hrvacationapp.util;

import java.sql.Connection;

public abstract class EmployeeDataAccessObject<T extends DataTransferObject> {
    private final Connection connection;

    public EmployeeDataAccessObject(Connection connection) {
        this.connection = connection;
    }

}
