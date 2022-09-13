package com.mansourappdevelopment.hrvacationapp.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private Connection connection;

    public DBManager(Connection connection) {
        this.connection = connection;
    }

    //This method is called once to add the employees table and boilerplate it with some random employees
    public void setUpDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS EMPLOYEES");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS EMPLOYEES (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT," +
                    "LAST_NAME TEXT, USER_NAME TEXT UNIQUE, PASSWORD TEXT, ANNUAL_VACATION INTEGER, SICK_VACATION INTEGER)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Mahmoud', 'Mansour', 'mahmoudMansour', 'mahmoudMansour', 15, 7)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Khaled', 'Samir', 'khaledSamir', 'khaledSamir', 15, 7)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Ahmed', 'Ali', 'ahmedAli', 'ahmedAli', 20, 8)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Mahmoud', 'Abdallah', 'mahmoudAbdallah', 'mahmoudAbdallah', 15, 7)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, ANNUAL_VACATION, SICK_VACATION) VALUES('Ahmed', 'Metwally', 'ahmedMetwally', 'ahmedMetwally', 20, 10)");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
