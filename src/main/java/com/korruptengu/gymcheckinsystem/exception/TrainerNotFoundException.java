package com.korruptengu.gymcheckinsystem.exception;

public class TrainerNotFoundException extends RuntimeException{
    public TrainerNotFoundException(Long id){
        super("Trainer with Id: " + id + " not found");
    }
}
