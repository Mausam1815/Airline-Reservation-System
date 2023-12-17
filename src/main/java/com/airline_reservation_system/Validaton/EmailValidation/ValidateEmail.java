package com.airline_reservation_system.Validaton.EmailValidation;

import com.airline_reservation_system.Exception.InvalidEmailException;
import com.airline_reservation_system.Utilities.EmailValidityCheck;

public class ValidateEmail {
    public static void validateEmail(String mail) {
        if (!EmailValidityCheck.checkEmailValidity(mail)) {
            throw new InvalidEmailException("Email is not valid. It must contain " + "'@'" + " and " + "'.'" + " symbols.");
        }
    }
}
