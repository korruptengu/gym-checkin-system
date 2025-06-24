package com.korruptengu.gymcheckinsystem.exception;

public class TrainingSessionNotFoundExeption extends RuntimeException {
    public TrainingSessionNotFoundExeption(Long id){
        super("TrainingSession with Id: " + id + " not found");
    }
}