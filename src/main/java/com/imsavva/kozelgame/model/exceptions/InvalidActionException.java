package com.imsavva.kozelgame.model.exceptions;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException() {
    }

    public InvalidActionException(String message) {
        super(message);
    }
}
