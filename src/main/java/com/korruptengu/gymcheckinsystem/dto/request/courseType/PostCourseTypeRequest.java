package com.korruptengu.gymcheckinsystem.dto.request.courseType;

import jakarta.validation.constraints.NotBlank;

public record PostCourseTypeRequest(
        @NotBlank String title,
        String description,
        @NotBlank String category
) {}
