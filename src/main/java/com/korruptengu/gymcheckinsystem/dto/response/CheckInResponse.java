package com.korruptengu.gymcheckinsystem.dto.response;

import java.time.LocalDateTime;

public record CheckInResponse(
        Long id,
        LocalDateTime checkInTime,
        LocalDateTime checkOutTime,
        Long memberId
) {}
