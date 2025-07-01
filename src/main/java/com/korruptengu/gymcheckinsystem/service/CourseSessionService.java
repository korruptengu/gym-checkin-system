package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.courseSession.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseSessionResponse;

import java.util.List;

public interface CourseSessionService {
    List<CourseSessionResponse> getAllCourseSessions();
    CourseSessionResponse getCourseSessionById(Long id);
    CourseSessionResponse createCourseSession(PostCourseSessionRequest request);
    CourseSessionResponse deleteCourseSessionById(Long id);
    CourseSessionResponse updateCourseSessionCompletely(Long id, PutCourseSessionRequest request);
    CourseSessionResponse updateCourseSessionPartially(Long id, PatchCourseSessionRequest request);
}
