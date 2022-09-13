package com.mansourappdevelopment.hrvacationapp.dao;

import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.model.User;
import com.mansourappdevelopment.hrvacationapp.util.DataTransferObject;
import com.mansourappdevelopment.hrvacationapp.util.HRDataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HRDAO extends HRDataAccessObject<Employee> {
    public HRDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Employee findEmployeeById(int id) {
        String findEmployeeById = "SELECT * FROM EMPLOYEES WHERE ID  = ?";
        Employee employee = null;
        try (PreparedStatement statement = this.connection.prepareStatement(findEmployeeById)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String getAllEmployees = "SELECT * FROM EMPLOYEES";
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getAllEmployees)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void createNewEmployee(Employee dto) {
        String INSERT = "INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, ANNUAL_VACATION, SICK_VACATION) VALUES (?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getFirstName());
            statement.setString(2, dto.getLastName());
            statement.setInt(3, dto.getAnnualVacationCount());
            statement.setInt(4, dto.getSickVacationCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //TODO:: implement the update method
    @Override
    public void updateEmployee(Employee dto) {
    }

    //TODO:: implement the delete method
    @Override
    public void deleteEmployee(long id) {
    }

}
