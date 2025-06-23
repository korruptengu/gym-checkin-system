package com.korruptengu.gymcheckinsystem.exception;

public class TrainMemberNotFoundException extends RuntimeException{
    public TrainMemberNotFoundException(Long trainerId, Long memberId){
        super("Trainer with Id: " + trainerId + " and Member with Id: " + memberId + " not found");
    }
}
