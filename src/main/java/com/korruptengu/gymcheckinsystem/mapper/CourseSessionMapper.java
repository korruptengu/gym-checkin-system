package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.courseSession.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseSessionMapper {
    default CourseSessionResponse toResponse(CourseSession session) {
        if (session == null) return null;

        return new CourseSessionResponse(
                session.getId(),
                session.getStartTime(),
                session.getDurationInMinutes(),
                session.getCourseType() != null
                        ? session.getCourseType().getId()
                        : null,
                session.getTrainer() != null
                        ? session.getTrainer().getId()
                        : null
        );
    }

    @Mapping(target = "id", ignore = true)
    CourseSession putRequestToEntity(PutCourseSessionRequest request);

    @Mapping(target = "id", ignore = true)
    CourseSession postRequestToEntity(PostCourseSessionRequest request);

    @Mapping(target = "id", ignore = true)
    CourseSession patchRequestToEntity(PatchCourseSessionRequest request);
}
