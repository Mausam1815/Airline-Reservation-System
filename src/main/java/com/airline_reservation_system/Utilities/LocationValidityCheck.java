package com.airline_reservation_system.Utilities;

import com.airline_reservation_system.Enum.Airports;

public class LocationValidityCheck {
    public static boolean checkLocationValidity(String location) {
        for(Airports airport : Airports.values()) {
            if(location.toUpperCase().equals(airport.toString())) {
                return true;
            }
        }
        return false;
    }
}
