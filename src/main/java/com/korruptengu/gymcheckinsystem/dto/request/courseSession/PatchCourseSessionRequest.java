package com.korruptengu.gymcheckinsystem.dto.request.courseSession;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.entity.CourseType;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.Duration;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchCourseSessionRequest(
        LocalDateTime startTime,
        Integer durationInMinutes,
        Long courseTypeId,
        Long trainerId) {

}
