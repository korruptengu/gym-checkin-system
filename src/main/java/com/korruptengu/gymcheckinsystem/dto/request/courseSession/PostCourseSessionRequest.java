package com.korruptengu.gymcheckinsystem.dto.request.courseSession;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public record PostCourseSessionRequest(
        @NotNull LocalDateTime startTime,
        @NotNull Integer durationInMinutes,
        @Min(1) @NotNull Long courseTypeId,
        @Min(1) @NotNull Long trainerId)
        {}
