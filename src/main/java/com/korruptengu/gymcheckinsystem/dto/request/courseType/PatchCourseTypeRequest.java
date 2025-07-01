package com.korruptengu.gymcheckinsystem.dto.request.courseType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.entity.MemberState;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchCourseTypeRequest(
         String title,
         String description,
         String category) {
}
