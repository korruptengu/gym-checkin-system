package com.korruptengu.gymcheckinsystem.exception;

public class TrainTeacherNotFoundException extends RuntimeException{
    public TrainTeacherNotFoundException(Long trainerId, Long memberId){
        super("Instructor with Id: " + trainerId + " and Student with Id: " + memberId + " not found");
    }
}
