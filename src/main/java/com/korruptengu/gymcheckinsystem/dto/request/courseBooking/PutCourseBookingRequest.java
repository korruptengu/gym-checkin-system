package com.korruptengu.gymcheckinsystem.dto.request.courseBooking;

import com.korruptengu.gymcheckinsystem.enums.BookingStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PutCourseBookingRequest(
        @Min(1) @NotNull Long memberId,
        @Min(1) @NotNull Long courseSessionId,
        @NotNull BookingStatus status,
        boolean attended) {}
