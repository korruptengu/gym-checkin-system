package com.korruptengu.gymcheckinsystem.dto.response;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

public record TrainingSessionResponse(
    Long id,
    LocalDateTime startTime,
    Integer durationInMinutes,
    Long trainerId,
    Long memberId){}
