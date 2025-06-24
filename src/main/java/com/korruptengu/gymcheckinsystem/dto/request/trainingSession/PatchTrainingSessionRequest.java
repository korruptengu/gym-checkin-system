package com.korruptengu.gymcheckinsystem.dto.request.trainingSession;

import com.fasterxml.jackson.annotation.JsonInclude;


import java.time.Duration;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchTrainingSessionRequest(
        LocalDateTime startTime,
        Duration duration,
        Long trainerId,
        Long memberId) {

}
