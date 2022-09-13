package com.mansourappdevelopment.hrvacationapp.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

//Just basic validations against No TextField input
//TODO:: Add regular expressions checks on both the username, password and id
public class Validator {
    public static boolean validUserName(String userName) {
        return !userName.equals("");
    }

    public static boolean validPassword(String password) {
        return !password.equals("");
    }

    public static boolean validId(String id) {
        return !id.equals("");
    }

    public static boolean validFirstName(String firstName) {
        return !firstName.equals("");
    }

    public static boolean validLastName(String lastName) {
        return !lastName.equals("");
    }

    public static boolean validAnnualVacation(String annualVacation) {
        return !annualVacation.equals("");
    }

    public static boolean validSickVacation(String sickVacation) {
        return !sickVacation.equals("");
    }

    public static boolean tableExists(Connection connection) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "EMPLOYEES",
                    new String[]{"TABLE"});
            resultSet.next();
            if (resultSet.getString("TABLE_NAME") != null && resultSet.getString("TABLE_NAME").equals("EMPLOYEES"))
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
