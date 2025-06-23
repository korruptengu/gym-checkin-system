package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.CourseBooking;
import com.korruptengu.gymcheckinsystem.exception.CourseBookingNotFoundExeption;
import com.korruptengu.gymcheckinsystem.repository.CourseBookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBookingService {
    private final CourseBookingRepository courseBookingRepository;

    public CourseBookingService(CourseBookingRepository courseBookingRepository) {
        this.courseBookingRepository = courseBookingRepository;
    }

    public List<CourseBooking> getAllCourseBooking(){
        return courseBookingRepository.findAll();
    }

    public CourseBooking getCourseBookingById(Long id){
        return courseBookingRepository.findById(id)
                .orElseThrow(() -> new CourseBookingNotFoundExeption(id));
    }

    public CourseBooking createCourseBooking(CourseBooking courseBooking){
        if(courseBooking == null) throw new IllegalArgumentException("New data must be not null");
        return courseBookingRepository.save(courseBooking);
    }

    public CourseBooking deleteCourseBooking(Long id){
        CourseBooking deletedCourseBooking = courseBookingRepository.findById(id)
                .orElseThrow(() -> new CourseBookingNotFoundExeption(id));
        courseBookingRepository.delete(deletedCourseBooking);
        return deletedCourseBooking;
    }

    public CourseBooking updateCourseBooking(Long id, CourseBooking updated){
        if (updated == null) throw new IllegalArgumentException("Update data must not be null");
        CourseBooking existingCourseBooking = courseBookingRepository.findById(id)
                .orElseThrow(() -> new CourseBookingNotFoundExeption(id));
        if (updated.getCourseSession() != null) existingCourseBooking.setCourseSession(updated.getCourseSession());
        if (updated.getMember() != null) existingCourseBooking.setMember(updated.getMember());
        return courseBookingRepository.save(existingCourseBooking);
    }

}
