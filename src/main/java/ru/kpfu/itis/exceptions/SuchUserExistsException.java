package ru.kpfu.itis.exceptions;

public class SuchUserExistsException extends Exception {

    public SuchUserExistsException(String message) {
        super(message);
    }
}
