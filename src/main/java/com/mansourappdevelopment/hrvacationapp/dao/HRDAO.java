package com.mansourappdevelopment.hrvacationapp.dao;

import com.mansourappdevelopment.hrvacationapp.model.Employee;
import com.mansourappdevelopment.hrvacationapp.model.Vacation;
import com.mansourappdevelopment.hrvacationapp.util.Constants;
import com.mansourappdevelopment.hrvacationapp.util.HRDataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HRDAO extends HRDataAccessObject<Employee, Vacation> {
    public HRDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Employee getEmployeeById(int id) {
        String select = "SELECT * FROM EMPLOYEES WHERE ID  = ?";
        Employee employee = null;
        try (PreparedStatement statement = this.connection.prepareStatement(select)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee getEmployeeByUserName(String userName) {
        String select = "SELECT * FROM EMPLOYEES WHERE USER_NAME  = ?";
        Employee employee = null;
        try (PreparedStatement statement = this.connection.prepareStatement(select)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7));
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
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void createNewEmployee(Employee dto) {
        String INSERT = "INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
            statement.setString(1, dto.getFirstName());
            statement.setString(2, dto.getLastName());
            statement.setString(3, dto.getUserName());
            statement.setString(4, dto.getPassword());
            statement.setInt(5, dto.getAnnualVacationCount());
            statement.setInt(6, dto.getSickVacationCount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee updateEmployee(Employee dto) {
        String update = "UPDATE EMPLOYEES SET FIRST_NAME=?, LAST_NAME=?, USER_NAME=?, PASSWORD=?, ANNUAL_VACATION=?, SICK_VACATION=? WHERE ID=?";
        try (PreparedStatement statement = this.connection.prepareStatement(update)) {
            statement.setString(1, dto.getFirstName());
            statement.setString(2, dto.getLastName());
            statement.setString(3, dto.getUserName());
            statement.setString(4, dto.getPassword());
            statement.setInt(5, dto.getAnnualVacationCount());
            statement.setInt(6, dto.getSickVacationCount());
            statement.setInt(7, dto.getId());
            statement.executeUpdate();
            return getEmployeeById(dto.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteEmployee(int id) {
        String delete = "DELETE FROM EMPLOYEES WHERE ID = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(delete)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vacation> getAllVacations() {
        String getAllVacations = "SELECT V.ID, V.EMPLOYEE_ID, E.FIRST_NAME" +
                ", E.LAST_NAME, V.VACATION_TYPE, V.VACATION_DAYS_COUNT " +
                "FROM VACATIONS AS V INNER JOIN EMPLOYEES AS E ON V.EMPLOYEE_ID = E.ID";
        List<Vacation> vacations = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(getAllVacations)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vacation vacation = new Vacation(resultSet.getString(5), resultSet.getInt(6));
                vacation.setVacationId(resultSet.getInt(1));
                vacation.setEmployeeId(resultSet.getInt(2));
                vacation.setEmployeeFirstName(resultSet.getString(3));
                vacation.setEmployeeLastName(resultSet.getString(4));
                vacations.add(vacation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return vacations;
    }

    @Override
    public void rejectVacation(int vacationId) {
        String rejectVacation = "DELETE FROM VACATIONS WHERE ID = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(rejectVacation)) {
            statement.setInt(1, vacationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasVacationDaysLeft(Vacation vacation) {
        String getVacationDaysLeft;
        if (vacation.getType().equals(Constants.ANNUAL_VACATION))
            getVacationDaysLeft = "SELECT ANNUAL_VACATION FROM EMPLOYEES WHERE ID = ?";
        else
            getVacationDaysLeft = "SELECT SICK_VACATION FROM EMPLOYEES WHERE ID = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(getVacationDaysLeft)) {
            statement.setInt(1, vacation.getEmployeeId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) >= vacation.getDaysCount();
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void acceptVacation(Vacation vacation) {
        String acceptVacation;
        if (vacation.getType().equals(Constants.ANNUAL_VACATION))
            acceptVacation = "UPDATE EMPLOYEES SET ANNUAL_VACATION = ANNUAL_VACATION - ? WHERE ID = ?";
        else
            acceptVacation = "UPDATE EMPLOYEES SET SICK_VACATION = SICK_VACATION - ? WHERE ID = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(acceptVacation)) {
            statement.setInt(1, vacation.getDaysCount());
            statement.setInt(2, vacation.getEmployeeId());
            statement.executeUpdate();
            rejectVacation(vacation.getVacationId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
