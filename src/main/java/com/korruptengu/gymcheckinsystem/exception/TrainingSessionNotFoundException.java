package com.korruptengu.gymcheckinsystem.exception;

public class TrainingSessionNotFoundException extends RuntimeException {
    public TrainingSessionNotFoundException(Long id){
        super("TrainingSession with Id: " + id + " not found");
    }
}