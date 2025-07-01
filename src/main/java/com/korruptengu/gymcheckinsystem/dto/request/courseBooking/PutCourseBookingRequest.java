package com.korruptengu.gymcheckinsystem.dto.request.courseBooking;

import com.korruptengu.gymcheckinsystem.entity.BookingStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PutCourseBookingRequest(
        @Min(1) @NotNull Long memberId,
        @Min(1) @NotNull Long courseSessionId,
        @NotNull BookingStatus status,
        boolean attended) {}
