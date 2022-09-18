package com.mansourappdevelopment.hrvacationapp.dao;

import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.util.EmployeeDataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO extends EmployeeDataAccessObject<Employee> {
    public EmployeeDAO(Connection connection) {
        super(connection);
    }


    @Override
    public void createVacationRequest(Employee dto) {
        String INSERT = "INSERT INTO VACATIONS (EMPLOYEE_ID, VACATION_TYPE, VACATION_DAYS_COUNT) VALUES (?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getVacationRequest().getType());
            statement.setInt(3, dto.getVacationRequest().getDaysCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
