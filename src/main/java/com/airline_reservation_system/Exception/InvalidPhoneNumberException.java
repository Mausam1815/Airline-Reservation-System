package com.airline_reservation_system.Exception;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
