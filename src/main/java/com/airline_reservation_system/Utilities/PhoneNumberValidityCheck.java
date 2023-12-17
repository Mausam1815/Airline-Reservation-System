package com.airline_reservation_system.Utilities;

public class PhoneNumberValidityCheck {
    public static boolean checkPhoneNumberValidity(String phoneNumber) {
        if(phoneNumber.length() != 10) {
            return false;
        }
        for(char c : phoneNumber.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
