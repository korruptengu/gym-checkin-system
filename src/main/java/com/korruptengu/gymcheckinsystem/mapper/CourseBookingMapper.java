package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.PatchCourseBookingRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.PostCourseBookingRequest;
import com.korruptengu.gymcheckinsystem.dto.request.courseBooking.PutCourseBookingRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CourseBookingResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseBooking;
import com.korruptengu.gymcheckinsystem.entity.CourseBookingId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseBookingMapper {
    default CourseBookingResponse toResponse(CourseBooking courseBooking) {
        if (courseBooking == null) return null;

        return new CourseBookingResponse(
                courseBooking.getMember() != null
                        ? courseBooking.getMember().getId()
                        : null,
                courseBooking.getCourseSession() != null
                        ? courseBooking.getCourseSession().getId()
                        : null,
                courseBooking.getStatus(),
                courseBooking.isAttended()

        );
    }

    @Mapping(target = "member", ignore = true)
    @Mapping(target = "courseSession", ignore = true)
    CourseBooking putRequestToEntity(PutCourseBookingRequest request);

    @Mapping(target = "member", ignore = true)
    @Mapping(target = "courseSession", ignore = true)
    CourseBooking postRequestToEntity(PostCourseBookingRequest request);

    @Mapping(target = "member", ignore = true)
    @Mapping(target = "courseSession", ignore = true)
    CourseBooking patchRequestToEntity(PatchCourseBookingRequest request);

    default CourseBookingId postRequestToCourseBookingId(PostCourseBookingRequest request){
        return new CourseBookingId(request.memberId(), request.courseSessionId());
    }

    default CourseBookingId putRequestToCourseBookingId(PutCourseBookingRequest request){
        return new CourseBookingId(request.memberId(), request.courseSessionId());
    }

    default CourseBookingId patchRequestToCourseBookingId(PatchCourseBookingRequest request){
        return new CourseBookingId(request.memberId(), request.courseSessionId());
    }

}
