package com.korruptengu.gymcheckinsystem.dto.request.courseType;

import com.korruptengu.gymcheckinsystem.enums.MemberState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PutCourseTypeRequest(
        @NotBlank String title,
        String description,
        @NotBlank String category,
        @NotNull MemberState state
) {}
