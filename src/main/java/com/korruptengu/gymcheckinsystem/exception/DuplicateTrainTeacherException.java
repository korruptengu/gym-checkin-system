package com.korruptengu.gymcheckinsystem.exception;

public class DuplicateTrainTeacherException extends RuntimeException {
    public DuplicateTrainTeacherException(Long instructorId, Long studentId){
        super("TrainTeacher with Instructor Id: " + " and " + instructorId + "Student Id: " + studentId + "already exists" );
    }
}
