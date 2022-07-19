package com.projects.meetdeals.Service;

public class ItemNotExitException extends RuntimeException {
    public ItemNotExitException(String message) {
        super(message);
    }
}
