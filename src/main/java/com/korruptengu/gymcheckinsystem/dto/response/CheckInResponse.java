package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.entity.MemberState;

import java.time.LocalDateTime;

public record CheckInResponse(
        Long id,
        LocalDateTime checkInTime,
        LocalDateTime checkOutTime,
        Long memberId
) {}
