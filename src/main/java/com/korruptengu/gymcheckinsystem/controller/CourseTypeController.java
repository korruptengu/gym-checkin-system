package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.dto.request.courseType.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseTypeResponse;
import com.korruptengu.gymcheckinsystem.service.CourseTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(COURSE_TYPES)
@AllArgsConstructor
public class CourseTypeController {
    private final CourseTypeService service;

    @GetMapping
    public ResponseEntity<List<CourseTypeResponse>> getAllCourseTypes() {
        List<CourseTypeResponse> all = service.getAllCourseTypes();
        return ResponseEntity.ok(all);
    }

    @GetMapping(ID)
    public ResponseEntity<CourseTypeResponse> getCourseTypeById(@PathVariable Long id) {
        CourseTypeResponse courseType = service.getCourseTypeById(id);
        return ResponseEntity.ok(courseType);
    }

    @PostMapping
    public ResponseEntity<CourseTypeResponse> createCourseType(@Valid @RequestBody PostCourseTypeRequest request) {
        CourseTypeResponse created = service.createCourseType(request);
        URI location = URI.create(COURSE_TYPES + "/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(ID)
    public ResponseEntity<CourseTypeResponse> deleteCourseType(@PathVariable Long id) {
        CourseTypeResponse deleted = service.deleteCourseTypeById(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping(ID)
    public ResponseEntity<CourseTypeResponse> updateCourseType(@PathVariable Long id, @Valid @RequestBody PutCourseTypeRequest request) {
        CourseTypeResponse updated = service.updateCourseTypeCompletely(id, request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping(ID)
    public ResponseEntity<CourseTypeResponse> partiallyUpdateCourseType(@PathVariable Long id, @RequestBody PatchCourseTypeRequest request) {
        CourseTypeResponse updated = service.updateCourseTypePartially(id, request);
        return ResponseEntity.ok(updated);
    }
}
