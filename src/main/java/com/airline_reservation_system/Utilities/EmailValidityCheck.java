package com.airline_reservation_system.Utilities;

public class EmailValidityCheck {
    public static boolean checkEmailValidity(String email) {
        return email.contains("@") && email.contains(".");
    }
}
