package com.airline_reservation_system.Exception;

public class InvalidPasswordFormatException extends RuntimeException{
    public InvalidPasswordFormatException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
