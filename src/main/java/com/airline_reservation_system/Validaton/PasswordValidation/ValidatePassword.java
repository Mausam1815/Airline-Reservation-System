package com.airline_reservation_system.Validaton.PasswordValidation;

import com.airline_reservation_system.Exception.InvalidPasswordFormatException;
import com.airline_reservation_system.Utilities.PasswordValidityCheck;

public class ValidatePassword {
    public static void validatePassword(String password) {
        if(!PasswordValidityCheck.checkPasswordValidity(password)) {
            throw new InvalidPasswordFormatException("""
                    Invalid Password format. It must contain:
                    -> (a to z) lowercase,
                    -> (A to Z) uppercase,
                    -> (0 to 9) numeric value,
                    -> ('!', '@', '#', '$', '%', '&', '*', '-', '_', '=', '+', ',', '.') symbols.""");
        }
    }
}
