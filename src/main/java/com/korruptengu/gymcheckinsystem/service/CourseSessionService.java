package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.exception.CourseSessionNotFoundExeption;
import com.korruptengu.gymcheckinsystem.repository.CourseSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSessionService {
    private final CourseSessionRepository courseSessionRepository;

    public CourseSessionService(CourseSessionRepository courseSessionRepository) {
        this.courseSessionRepository = courseSessionRepository;
    }

    public List<CourseSession> getAllCourseSessions(){
        return courseSessionRepository.findAll();
    }

    public CourseSession getCourseSessionById(Long id){
        return courseSessionRepository.findById(id)
                .orElseThrow(() -> new CourseSessionNotFoundExeption(id));
    }

    public CourseSession createCourseSession(CourseSession courseSession){
        if(courseSession == null) throw new IllegalArgumentException("New data must not be null");
        return courseSessionRepository.save(courseSession);
    }

    public CourseSession deleteCourseSession(Long id){
        CourseSession courseSession = courseSessionRepository.findById(id)
                .orElseThrow(() -> new CourseSessionNotFoundExeption(id));
        courseSessionRepository.delete(courseSession);
        return courseSession;
    }

    public CourseSession updateCourseSession(Long id, CourseSession updateCourseSession){
        if (updateCourseSession == null) throw new IllegalArgumentException("Update data must be not null");
        CourseSession existingCourseSession = courseSessionRepository.findById(id)
                .orElseThrow(() -> new CourseSessionNotFoundExeption(id));
        if (updateCourseSession.getDuration() != null) existingCourseSession.setDuration(updateCourseSession.getDuration());
        return courseSessionRepository.save(existingCourseSession);
    }
}
