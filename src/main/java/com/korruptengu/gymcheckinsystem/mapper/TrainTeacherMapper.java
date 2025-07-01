package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.trainTeacher.PostTrainTeacherRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainTeacherResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacher;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacherId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TrainTeacherMapper {
    default TrainTeacherResponse toResponse(TrainTeacher trainTeacher) {
        return new TrainTeacherResponse(
                trainTeacher.getInstructor().getId(),
                trainTeacher.getStudent().getId());
    }

    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "student", ignore = true)
    TrainTeacher postRequestToEntity(PostTrainTeacherRequest request);

    default TrainTeacherId postRequestToTrainTeacherId(PostTrainTeacherRequest request) {
        return new TrainTeacherId(request.instructorId(), request.studentId());
    }
}
