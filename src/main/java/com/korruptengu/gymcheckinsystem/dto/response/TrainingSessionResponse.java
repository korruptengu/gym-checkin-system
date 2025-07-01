package com.korruptengu.gymcheckinsystem.dto.response;

import java.time.LocalDateTime;

public record TrainingSessionResponse(
    Long id,
    LocalDateTime startTime,
    Integer durationInMinutes,
    Long trainerId,
    Long memberId){}
