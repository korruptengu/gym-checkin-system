package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.entity.CourseBooking;
import com.korruptengu.gymcheckinsystem.service.CourseBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(COURSE_BOOKINGS)
public class CourseBookingController {
    private final CourseBookingService courseBookingService;

    public CourseBookingController(CourseBookingService courseBookingService) {
        this.courseBookingService = courseBookingService;
    }

    @GetMapping
    public List<CourseBooking> getAllCourseBookings (){
        return courseBookingService.getAllCourseBooking();
    }

    @GetMapping(ID)
    public CourseBooking getCourseBookingById(@PathVariable Long id){
        return courseBookingService.getCourseBookingById(id);
    }

    @PostMapping
    public ResponseEntity<CourseBooking> createCourseBooking(@RequestBody CourseBooking courseBooking){
        CourseBooking createdCourseBooking = courseBookingService.createCourseBooking(courseBooking);
        URI location = URI.create(COURSE_BOOKINGS + "/" + createdCourseBooking.getId());
        return ResponseEntity.created(location).body(createdCourseBooking);

    }

    @DeleteMapping(ID)
    public ResponseEntity<CourseBooking> deleteCourseBooking(@PathVariable Long id){
        return ResponseEntity.ok(courseBookingService.deleteCourseBooking(id));
    }

    @PutMapping(ID)
    public ResponseEntity<CourseBooking> updateCouresBooking(@PathVariable Long id, @RequestBody CourseBooking updated){
        return ResponseEntity.ok(courseBookingService.updateCourseBooking(id, updated));
    }
}
