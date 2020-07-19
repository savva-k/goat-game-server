package com.imsavva.kozelgame.model.exceptions;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException() {
    }

    public TableNotFoundException(String message) {
        super(message);
    }
}
