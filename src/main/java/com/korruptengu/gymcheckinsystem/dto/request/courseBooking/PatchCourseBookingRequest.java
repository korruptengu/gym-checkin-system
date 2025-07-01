package com.korruptengu.gymcheckinsystem.dto.request.courseBooking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.entity.BookingStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchCourseBookingRequest(
        Long memberId,
        Long courseSessionId,
        BookingStatus status,
        boolean attended) {

}
