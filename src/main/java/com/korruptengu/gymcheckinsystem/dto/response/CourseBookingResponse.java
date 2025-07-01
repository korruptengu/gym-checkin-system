package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.entity.BookingStatus;

import java.time.LocalDateTime;

public record CourseBookingResponse(
        Long memberId,
        Long courseBookingId,
        BookingStatus status,
        boolean attended
) {}
