package com.korruptengu.gymcheckinsystem.dto.request.trainingSession;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;

public record PostTrainingSessionRequest(
        @NotNull LocalDateTime startTime,
        @NotNull Duration duration,
        @Min(1) @NotNull Long trainerId,
        @Min(1) @NotNull Long memberId) {
}
