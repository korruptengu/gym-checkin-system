package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.entity.CourseType;
import com.korruptengu.gymcheckinsystem.service.CourseTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(COURSE_TYPES)
public class CourseTypeController {
    private final CourseTypeService courseTypeService;

    public CourseTypeController(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }

    @GetMapping
    public List<CourseType> getAllCourseTypes(){
        return courseTypeService.getAllCourseTypes();
    }

    @GetMapping(ID)
    public CourseType getCourseTypeById(@PathVariable Long id){
        return courseTypeService.getCourseTypByID(id);
    }

    @PostMapping
    public ResponseEntity<CourseType> createCourseType(@RequestBody CourseType courseType){
        CourseType createdCourseType = courseTypeService.createCourseType(courseType);
        URI location = URI.create(COURSE_TYPES + "/" + createdCourseType.getId());
        return ResponseEntity.created(location).body(createdCourseType);
    }

    @DeleteMapping(ID)
    public ResponseEntity<CourseType> deleteCourseType(@PathVariable Long id){
        return ResponseEntity.ok(courseTypeService.deleteCourseType(id));
    }

    @PutMapping(ID)
    public CourseType updateCourseType(@PathVariable Long id, @RequestBody CourseType updated){
        return courseTypeService.updateCourseType(id, updated);
    }

}
