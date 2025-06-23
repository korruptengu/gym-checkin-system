package com.korruptengu.gymcheckinsystem.exception;

public class EmptyUpdateDataException extends RuntimeException {
    public EmptyUpdateDataException() {
        super("At least one field must be provided for update");
    }
}
