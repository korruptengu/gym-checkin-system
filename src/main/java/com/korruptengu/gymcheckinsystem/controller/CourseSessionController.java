package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.dto.request.courseSession.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseSessionResponse;
import com.korruptengu.gymcheckinsystem.service.CourseSessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

@RestController
@RequestMapping(COURSE_SESSIONS)
@AllArgsConstructor
public class CourseSessionController {

    private final CourseSessionService service;

    @GetMapping
    public ResponseEntity<List<CourseSessionResponse>> getAllCourseSessions() {
        List<CourseSessionResponse> allSessions = service.getAllCourseSessions();
        return ResponseEntity.ok(allSessions);
    }

    @GetMapping(ID)
    public ResponseEntity<CourseSessionResponse> getCourseSessionById(@PathVariable Long id) {
        CourseSessionResponse session = service.getCourseSessionById(id);
        return ResponseEntity.ok(session);
    }

    @PostMapping
    public ResponseEntity<CourseSessionResponse> createCourseSession(@Valid @RequestBody PostCourseSessionRequest request) {
        CourseSessionResponse created = service.createCourseSession(request);
        URI location = URI.create(COURSE_SESSIONS + "/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(ID)
    public ResponseEntity<CourseSessionResponse> deleteCourseSession(@PathVariable Long id) {
        CourseSessionResponse deleted = service.deleteCourseSessionById(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping(ID)
    public ResponseEntity<CourseSessionResponse> updateCourseSession(
            @PathVariable Long id,
            @Valid @RequestBody PutCourseSessionRequest request
    ) {
        CourseSessionResponse updated = service.updateCourseSessionCompletely(id, request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping(ID)
    public ResponseEntity<CourseSessionResponse> partiallyUpdateCourseSession(
            @PathVariable Long id,
            @RequestBody PatchCourseSessionRequest request
    ) {
        CourseSessionResponse updated = service.updateCourseSessionPartially(id, request);
        return ResponseEntity.ok(updated);
    }
}