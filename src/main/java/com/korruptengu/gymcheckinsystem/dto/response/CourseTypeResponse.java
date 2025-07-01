package com.korruptengu.gymcheckinsystem.dto.response;

public record CourseTypeResponse(
        Long id,
        String title,
        String description,
        String category
) {}
