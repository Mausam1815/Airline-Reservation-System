package com.airline_reservation_system.Validaton.LocationValidation;

import com.airline_reservation_system.Exception.InvalidLocationException;
import com.airline_reservation_system.Utilities.LocationValidityCheck;

public class ValidateLocation {
    public static void validateLocation(String location) {
        if(!LocationValidityCheck.checkLocationValidity(location)) {
            throw new InvalidLocationException("Location is invalid. Please enter available locations only.");
        }
    }
}
