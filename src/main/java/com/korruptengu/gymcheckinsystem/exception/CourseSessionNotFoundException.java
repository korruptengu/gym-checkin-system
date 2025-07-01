package com.korruptengu.gymcheckinsystem.exception;

public class CourseSessionNotFoundException extends RuntimeException {
    public CourseSessionNotFoundException(Long id){
        super("CourseSession with Id: " + id + " not found");
    }
}