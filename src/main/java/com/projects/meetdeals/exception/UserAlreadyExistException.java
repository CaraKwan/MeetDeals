package com.projects.meetdeals.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message) {
       super(message);
    }
}
