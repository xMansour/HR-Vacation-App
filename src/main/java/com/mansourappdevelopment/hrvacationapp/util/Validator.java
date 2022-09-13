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
}
