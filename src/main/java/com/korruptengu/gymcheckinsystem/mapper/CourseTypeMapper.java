package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.courseType.PatchCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseType.PostCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseType.PutCourseTypeRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CourseTypeResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseTypeMapper {
    CourseTypeResponse toResponse(CourseType courseType);

    @Mapping(target = "id", ignore = true)
    CourseType putRequestToEntity(PutCourseTypeRequest request);

    @Mapping(target = "id", ignore = true)
    CourseType postRequestToEntity(PostCourseTypeRequest request);

    @Mapping(target = "id", ignore = true)
    CourseType patchRequestToEntity(PatchCourseTypeRequest request);
}
