package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.PatchCourseBookingRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.PostCourseBookingRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.PutCourseBookingRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CourseBookingResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseBookingId;
import com.korruptengu.gymcheckinsystem.service.CourseBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(COURSE_BOOKINGS)
public class CourseBookingController {
    private final CourseBookingService service;

    @GetMapping
    public ResponseEntity<List<CourseBookingResponse>> getAllCourseBookings (){
        List<CourseBookingResponse> allResponse = service.getAllCourseBookings();
        return ResponseEntity.ok(allResponse);
    }

    @GetMapping(COURSE_BOOKING_ID)
    public ResponseEntity<CourseBookingResponse> getCourseBookingById(@PathVariable Long memberId, @PathVariable Long courseSessionId){
        CourseBookingId id = toId(memberId, courseSessionId);
        CourseBookingResponse response = service.getCourseBookingById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CourseBookingResponse> createCourseBooking(@Valid @RequestBody PostCourseBookingRequest request){
        CourseBookingResponse response = service.createCourseBooking(request);
        URI location = URI.create(COURSE_BOOKINGS + "/" + response.memberId() + "/" +response.courseBookingId());
        return ResponseEntity.created(location).body(response);

    }

    @DeleteMapping(COURSE_BOOKING_ID)
    public ResponseEntity<CourseBookingResponse> deleteCourseBooking(@PathVariable Long memberId, @PathVariable Long courseSessionId){
        CourseBookingId id = toId(memberId, courseSessionId);
        CourseBookingResponse response = service.deleteCourseBookingById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(COURSE_BOOKING_ID)
    public ResponseEntity<CourseBookingResponse> updateCourseBooking (
            @PathVariable Long memberId,
            @PathVariable Long courseSessionId,
            @Valid @RequestBody PutCourseBookingRequest request) {

        CourseBookingId id = toId(memberId, courseSessionId);
        CourseBookingResponse response = service.updateCourseBookingCompletely(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(COURSE_BOOKING_ID)
    public ResponseEntity<CourseBookingResponse> partiallyUpdateCourseBooking(
            @PathVariable Long memberId,
            @PathVariable Long courseSessionId,
            @RequestBody PatchCourseBookingRequest request) {

        CourseBookingId id = new CourseBookingId(memberId, courseSessionId);
        CourseBookingResponse response = service.updateCourseBookingPartially(id, request);
        return ResponseEntity.ok(response);
    }

    private CourseBookingId toId(Long memberId, Long courseSessionId) {
        return new CourseBookingId(memberId, courseSessionId);
    }
}
