package com.airline_reservation_system.Exception;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
