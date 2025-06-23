package com.korruptengu.gymcheckinsystem.exception;

public class CheckInNotFoundException extends RuntimeException{
    public CheckInNotFoundException(Long id){
        super("CheckIn with Id: " + id + " not found");
    }
}

