package com.airline_reservation_system.Validaton.GenderValidation;

import com.airline_reservation_system.Exception.InvalidGenderFormatException;
import com.airline_reservation_system.Utilities.GenderValidityCheck;

public class ValidateGender {
    public static void validateGender(String gender) {
        if(!GenderValidityCheck.checkGenderValidity(gender)) {
            throw new InvalidGenderFormatException("Gender format is invalid. It must be in uppercase only. MALE, FEMALE and OTHER only these values.");
        }
    }
}
