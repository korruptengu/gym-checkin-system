package com.korruptengu.gymcheckinsystem.exception;

public class EmptyUpdateDataException extends RuntimeException {
    public EmptyUpdateDataException() {
        super("Es wurden keine Felder zum Aktualisieren übergeben.");
    }
}
