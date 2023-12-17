package com.airline_reservation_system.Exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String excpetionMessage) {
        super(excpetionMessage);
    }
}
