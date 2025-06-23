package com.korruptengu.gymcheckinsystem.exception;

public class CourseSessionNotFoundExeption extends RuntimeException {
    public CourseSessionNotFoundExeption(Long id){
        super("CourseSession with Id: " + id + " not found");
    }
}