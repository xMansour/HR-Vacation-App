package com.mansourappdevelopment.hrvacationapp.util;

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


}
