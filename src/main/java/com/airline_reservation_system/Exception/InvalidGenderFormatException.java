package com.airline_reservation_system.Exception;

public class InvalidGenderFormatException extends RuntimeException{
    public InvalidGenderFormatException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
