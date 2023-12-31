package com.airline_reservation_system.Exception;

public class InvalidTimeException extends RuntimeException{
    public InvalidTimeException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
