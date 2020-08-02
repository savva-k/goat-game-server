package com.imsavva.kozelgame.model.exceptions;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException() {}

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
