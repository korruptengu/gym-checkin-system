package com.korruptengu.gymcheckinsystem.dto.request.courseType;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchCourseTypeRequest(
         String title,
         String description,
         String category) {
}
