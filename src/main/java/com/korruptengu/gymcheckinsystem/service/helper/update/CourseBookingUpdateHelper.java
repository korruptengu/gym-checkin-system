package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.CourseBooking;

public class CourseBookingUpdateHelper {
    public static void updateCompletely(CourseBooking target, CourseBooking source){
        target.setCourseSession(source.getCourseSession());
        target.setMember(source.getMember());
    }

    public static void updatePartially(CourseBooking target, CourseBooking source){
        if (source.getCourseSession() != null) target.setCourseSession(source.getCourseSession());
        if (source.getMember() != null) target.setMember(source.getMember());
    }

    public static boolean isAllFieldsNull(CourseBooking courseBooking) {
        return  courseBooking.getMember() == null &&
                courseBooking.getCourseSession() == null;
    }
}
