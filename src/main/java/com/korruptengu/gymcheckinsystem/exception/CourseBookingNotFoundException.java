package com.korruptengu.gymcheckinsystem.exception;

import com.korruptengu.gymcheckinsystem.entity.CourseBookingId;

public class CourseBookingNotFoundException extends RuntimeException {
    public CourseBookingNotFoundException(Long memberId, Long courseSessionId){
        super("CourseSession with Member Id: " + memberId + " and CourseSession Id: " + courseSessionId + " not found");
    }
}