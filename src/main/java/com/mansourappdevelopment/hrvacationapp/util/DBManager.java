package com.mansourappdevelopment.hrvacationapp.util;

import java.sql.*;

public class DBManager {
    private Connection connection;

    public DBManager(Connection connection) {
        this.connection = connection;
    }

    //This method is called once to add the employees table
    public void setUpEmployeesDatabases() {
        try {
            Statement statement = connection.createStatement();
            //statement.executeUpdate("DROP TABLE IF EXISTS EMPLOYEES");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS EMPLOYEES (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT," +
                    "LAST_NAME TEXT, USER_NAME TEXT UNIQUE, PASSWORD TEXT, ANNUAL_VACATION INTEGER, SICK_VACATION INTEGER)");
            populateEmployeesTable(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //This method is called once to add the vacations table
    public void setUpVacationsDatabases() {
        try {
            Statement statement = connection.createStatement();
            //statement.executeUpdate("DROP TABLE IF EXISTS VACATIONS");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS VACATIONS (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMPLOYEE_ID INTEGER," +
                    "VACATION_TYPE TEXT, VACATION_DAYS_COUNT INTEGER, FOREIGN KEY(EMPLOYEE_ID) REFERENCES EMPLOYEES(ID))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void populateEmployeesTable(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Mahmoud', 'Mansour', 'mahmoudMansour', 'mahmoudMansour', 15, 7)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Khaled', 'Samir', 'khaledSamir', 'khaledSamir', 15, 7)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Ahmed', 'Ali', 'ahmedAli', 'ahmedAli', 20, 8)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Mahmoud', 'Abdallah', 'mahmoudAbdallah', 'mahmoudAbdallah', 15, 7)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Ali', 'Mohamed', 'aliMohamed', 'aliMohamed', 20, 10)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Ahmed', 'Metwally', 'ahmedMetwally', 'ahmedMetwally', 20, 10)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Hossam', 'Saad', 'hossamSaad', 'hossamSaad', 15, 7)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Ahmed', 'Ibrahim', 'ahmedIbrahim', 'ahmedIbrahim', 15, 10)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Mohamed', 'Hassan', 'mohamedHassan', 'mohamedHassan', 20, 10)");
        statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Hassan', 'Elsayed', 'hassanElsayed', 'hassanElsayed', 14, 7)");

    }

    public boolean employeesTableExists(Connection connection) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "EMPLOYEES",
                    new String[]{"TABLE"});
            resultSet.next();
            return resultSet.getString("TABLE_NAME") != null && resultSet.getString("TABLE_NAME").equals("EMPLOYEES");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean vacationsTableExists(Connection connection) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "VACATIONS",
                    new String[]{"TABLE"});
            resultSet.next();
            return resultSet.getString("TABLE_NAME") != null && resultSet.getString("TABLE_NAME").equals("VACATIONS");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
