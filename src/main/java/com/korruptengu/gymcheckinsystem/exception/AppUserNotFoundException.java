package com.korruptengu.gymcheckinsystem.exception;

public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException(Long id){
        super("AppUser with Id: " + id + " not found");
    }
}
