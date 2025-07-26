package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.enums.BookingStatus;

public record CourseBookingResponse(
        Long memberId,
        Long courseBookingId,
        BookingStatus status,
        boolean attended
) {}
