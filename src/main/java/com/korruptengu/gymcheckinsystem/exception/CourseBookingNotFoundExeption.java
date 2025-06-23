package com.korruptengu.gymcheckinsystem.exception;

public class CourseBookingNotFoundExeption extends RuntimeException {
    public CourseBookingNotFoundExeption(Long id){
        super("CourseBooking with Id: " + id + " not found");
    }
}