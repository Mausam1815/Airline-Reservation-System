package com.airline_reservation_system.Utilities;

public class PasswordValidityCheck {
    public static boolean checkPasswordValidity(String password) {
        if(password.length() < 8) {
            return false;
        }
        if(password.equals(password.toLowerCase())) {
            return false;
        }
        if(password.equals(password.toUpperCase())) {
            return false;
        }
        boolean containsDigit = false;
        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)) {
                containsDigit = true;
                break;
            }
        }
        if(!containsDigit) {
            return false;
        }
        String specialChars = "!@#$%^&*-_=+,.";
        boolean containsSpecialChar = false;
        for(char c : password.toCharArray()) {
            if(specialChars.contains(String.valueOf(c))) {
                containsSpecialChar = true;
                break;
            }
        }
        if(!containsSpecialChar) {
            return false;
        }
        return true;
    }
}
