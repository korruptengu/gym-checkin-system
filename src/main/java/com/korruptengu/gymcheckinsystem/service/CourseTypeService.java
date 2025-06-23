package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.CourseType;
import com.korruptengu.gymcheckinsystem.exception.CourseTypeNotFoundException;
import com.korruptengu.gymcheckinsystem.repository.CourseTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTypeService {
    private final CourseTypeRepository courseTypeRepository;

    public CourseTypeService(CourseTypeRepository courseTypeRepository) {
        this.courseTypeRepository = courseTypeRepository;
    }

    public List<CourseType> getAllCourseTypes(){
        return courseTypeRepository.findAll();
    }

    public CourseType getCourseTypByID (Long id){
        return courseTypeRepository.findById(id)
                .orElseThrow(() -> new CourseTypeNotFoundException(id));
    }

    public CourseType createCourseType(CourseType courseType){
        if (courseType == null) throw new IllegalArgumentException("New date must not be null");
        return courseTypeRepository.save(courseType);
    }

    public CourseType deleteCourseType(Long id){
        CourseType courseType = courseTypeRepository.findById(id)
                .orElseThrow(() -> new CourseTypeNotFoundException(id));
        courseTypeRepository.delete(courseType);
        return courseType;
    }

    public CourseType updateCourseType(Long id, CourseType updatedCourseType){
        if (updatedCourseType == null) throw new IllegalArgumentException("Update data must be not null");
        CourseType existingCourseType = courseTypeRepository.findById(id)
                .orElseThrow(() -> new CourseTypeNotFoundException(id));
        if (updatedCourseType.getCategory() != null) existingCourseType.setCategory((updatedCourseType.getCategory()));
        if (updatedCourseType.getTitle() != null) existingCourseType.setTitle(updatedCourseType.getTitle());
        if (updatedCourseType.getDescription() != null) existingCourseType.setDescription(updatedCourseType.getDescription());
        return courseTypeRepository.save(existingCourseType);
    }
}
