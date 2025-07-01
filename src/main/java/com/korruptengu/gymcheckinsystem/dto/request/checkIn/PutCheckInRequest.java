package com.korruptengu.gymcheckinsystem.dto.request.checkIn;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PutCheckInRequest(
        @NotNull LocalDateTime checkInTime,
        LocalDateTime checkOutTime,
        @Min(1) @NotNull Long memberId) {}
