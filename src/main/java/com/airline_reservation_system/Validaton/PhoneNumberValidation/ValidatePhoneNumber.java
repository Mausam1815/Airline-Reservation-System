package com.airline_reservation_system.Validaton.PhoneNumberValidation;

import com.airline_reservation_system.Exception.InvalidPhoneNumberException;
import com.airline_reservation_system.Utilities.PhoneNumberValidityCheck;

public class ValidatePhoneNumber {
    public static void validatePhoneNumber(String phoneNumber) {
        if(!PhoneNumberValidityCheck.checkPhoneNumberValidity(phoneNumber)) {
            throw new InvalidPhoneNumberException("Phone number format is not correct. It must have length of 10 digit and only numeric values 0 to 9.");
        }
    }
}
