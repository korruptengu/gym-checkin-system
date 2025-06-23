package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.response.TrainTeacherResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacher;

public class TrainTeacherMapper {
    public static TrainTeacherResponse toResponse(TrainTeacher trainTeacher) {
        return new TrainTeacherResponse(
                trainTeacher.getInstructor().getId(),
                trainTeacher.getStudent().getId());
    }
}
