package com.korruptengu.gymcheckinsystem.dto.response;

import java.time.Duration;
import java.time.LocalDateTime;

public record CourseSessionResponse(
        Long id,
        LocalDateTime startTime,
        Integer durationInMinutes,
        Long courseTypeId,
        Long trainerId) {}
