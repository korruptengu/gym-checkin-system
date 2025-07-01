package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.courseType.PatchCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseType.PostCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseType.PutCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CourseTypeResponse;

import java.util.List;

public interface CourseTypeService {
    List<CourseTypeResponse> getAllCourseTypes();
    CourseTypeResponse getCourseTypeById(Long id);
    CourseTypeResponse createCourseType(PostCourseTypeRequest request);
    CourseTypeResponse deleteCourseTypeById(Long id);
    CourseTypeResponse updateCourseTypeCompletely(Long id, PutCourseTypeRequest request);
    CourseTypeResponse updateCourseTypePartially(Long id, PatchCourseTypeRequest request);
}
