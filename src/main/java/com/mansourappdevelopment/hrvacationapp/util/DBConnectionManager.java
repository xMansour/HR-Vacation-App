package com.mansourappdevelopment.hrvacationapp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    private String dbUrl;
    private Properties properties;

    public DBConnectionManager(String propertiesFile) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFile));
            dbUrl = properties.getProperty("dbUrl");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
