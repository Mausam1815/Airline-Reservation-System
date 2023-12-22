package com.airline_reservation_system.Exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
