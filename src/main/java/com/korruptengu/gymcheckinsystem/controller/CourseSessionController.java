package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.service.CourseSessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(COURSE_SESSIONS)
public class CourseSessionController {
    private final CourseSessionService courseSessionService;

    public CourseSessionController(CourseSessionService courseSessionService) {
        this.courseSessionService = courseSessionService;
    }

    public List<CourseSession> getAllCourseSessions(){
        return courseSessionService.getAllCourseSessions();
    }

    @GetMapping(ID)
    public CourseSession getCourseSessionById(@PathVariable Long id){
        return courseSessionService.getCourseSessionById(id);
    }

    @PostMapping
    public ResponseEntity<CourseSession> createCourseSession(@RequestBody CourseSession courseSession){
        CourseSession createdCourseSession = courseSessionService.createCourseSession(courseSession);
        URI location = URI.create(COURSE_SESSIONS + "/" + createdCourseSession.getId());
        return ResponseEntity.created(location).body(createdCourseSession);
    }

    @DeleteMapping(ID)
    public ResponseEntity<CourseSession> deleteCourseSession(@PathVariable Long id){
        return ResponseEntity.ok(courseSessionService.deleteCourseSession(id));
    }

    @PutMapping(ID)
    public ResponseEntity<CourseSession> updateCourseSession(@PathVariable Long id, @RequestBody CourseSession updatedCourseSession){
        return ResponseEntity.ok(courseSessionService.updateCourseSession(id, updatedCourseSession));
    }

}
