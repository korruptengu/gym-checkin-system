package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseBookingResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseBookingId;

import java.util.List;

public interface CourseBookingService {
    List<CourseBookingResponse> getAllCourseBookings();
    CourseBookingResponse getCourseBookingById(CourseBookingId id);
    CourseBookingResponse createCourseBooking(PostCourseBookingRequest request);
    CourseBookingResponse deleteCourseBookingById(CourseBookingId id);
    CourseBookingResponse updateCourseBookingCompletely(CourseBookingId id, PutCourseBookingRequest request);
    CourseBookingResponse updateCourseBookingPartially(CourseBookingId id, PatchCourseBookingRequest request);
}
