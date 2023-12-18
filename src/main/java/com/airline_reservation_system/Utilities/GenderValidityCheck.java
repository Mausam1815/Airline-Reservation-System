package com.airline_reservation_system.Utilities;

import com.airline_reservation_system.Enum.Gender;

public class GenderValidityCheck {
    public static boolean checkGenderValidity(String gender) {
        return gender.toUpperCase().equals(Gender.MALE.toString()) || gender.toUpperCase().equals(Gender.FEMALE.toString()) || gender.toUpperCase().equals(Gender.OTHER.toString());
    }
}
