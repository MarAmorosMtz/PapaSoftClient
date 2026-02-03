package com.example.papasoftclient.utils;

public class SessionStore {

    private static String _username;
    private static int _accountType;


    public static String getUsername(String username) {
        return _username;
    }

    public static void setUsername(String username) {
        _username = username;
    }

    public static int getAccountType() {
        return _accountType;
    }

    public static void setAccountType(int accountType) {
        SessionStore._accountType = accountType;
    }
}
