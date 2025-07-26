package com.korruptengu.gymcheckinsystem.exception;

public class RoleConflictException extends RuntimeException {
    public RoleConflictException(String message) {
        super(message);
    }
}
