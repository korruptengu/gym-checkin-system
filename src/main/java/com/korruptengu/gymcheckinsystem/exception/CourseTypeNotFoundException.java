package com.korruptengu.gymcheckinsystem.exception;

public class CourseTypeNotFoundException extends RuntimeException {
    public CourseTypeNotFoundException(Long id){
        super("CourseType with Id: " + id + " not found");
    }
}