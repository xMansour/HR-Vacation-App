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

    public void setUpDatabase() {
        try {
            Statement statement = connection.createStatement();
            //statement.executeUpdate("DROP TABLE IF EXISTS EMPLOYEES");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS EMPLOYEES (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT," +
                    "LAST_NAME TEXT, ANNUAL_VACATION INTEGER, SICK_VACATION INTEGER)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, ANNUAL_VACATION, SICK_VACATION) VALUES('Mahmoud', 'Mansour', 15, 7)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, ANNUAL_VACATION, SICK_VACATION) VALUES('Khaled', 'Samir', 15, 7)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, ANNUAL_VACATION, SICK_VACATION) VALUES('Saeed', 'Mansour', 15, 8)");
            statement.executeUpdate("INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, ANNUAL_VACATION, SICK_VACATION) VALUES('Mohamed', 'Saeed', 20, 10)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
