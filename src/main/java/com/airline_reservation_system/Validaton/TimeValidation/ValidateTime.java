package com.airline_reservation_system.Validaton.TimeValidation;

import com.airline_reservation_system.Exception.InvalidTimeException;
import com.airline_reservation_system.Utilities.TimeValidityCheck;

public class ValidateTime {
    public static void validateTime(String time) {
        if(!TimeValidityCheck.checkTimeValidity(time)) {
            throw new InvalidTimeException("Inavalid time format. Must be in HH:MM fomrat.");
        }
    }
}
