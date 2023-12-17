package com.airline_reservation_system.Exception;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
