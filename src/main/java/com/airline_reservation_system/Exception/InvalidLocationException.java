package com.airline_reservation_system.Exception;

public class InvalidLocationException extends RuntimeException{
    public InvalidLocationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
