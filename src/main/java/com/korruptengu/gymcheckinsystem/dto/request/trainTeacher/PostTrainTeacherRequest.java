package com.korruptengu.gymcheckinsystem.dto.request.trainTeacher;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PostTrainTeacherRequest(
        @Min(1) @NotNull Long instructorId,
        @Min(1) @NotNull Long studentId
    ) {}
