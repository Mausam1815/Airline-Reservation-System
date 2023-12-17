package com.airline_reservation_system.Validaton.DateValidation;

import com.airline_reservation_system.Exception.InvalidDateException;
import com.airline_reservation_system.Utilities.DateValidityCheck;

public class ValidateDate {
    public static void validateDate(String dateOfBirth) {
        if(!DateValidityCheck.checkDateValidity(dateOfBirth)) {
            throw new InvalidDateException("Date is invalid. it must be in format: dd-mm-yyyy. Only contain numeric value and '-'.");
        }
    }
}
