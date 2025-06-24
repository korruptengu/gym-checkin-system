package com.korruptengu.gymcheckinsystem.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;

public record TrainingSessionResponse(
        Long id,
        LocalDateTime startTime,
        Duration duration,
        Long trainerId,
        Long memberId){}
