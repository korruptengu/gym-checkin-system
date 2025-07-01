package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PatchCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PostCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PutCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CheckInResponse;
import com.korruptengu.gymcheckinsystem.entity.CheckIn;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckInMapper {
    default CheckInResponse toResponse(CheckIn checkIn) {
        if (checkIn == null) return null;

        return new CheckInResponse(
                checkIn.getId(),
                checkIn.getCheckInTime(),
                checkIn.getCheckOutTime(),
                checkIn.getMember() != null ? checkIn.getMember().getId() : null
        );
    }

    @Mapping(target = "id", ignore = true)
    CheckIn putRequestToEntity(PutCheckInRequest request);

    @Mapping(target = "id", ignore = true)
    CheckIn postRequestToEntity(PostCheckInRequest request);

    @Mapping(target = "id", ignore = true)
    CheckIn patchRequestToEntity(PatchCheckInRequest request);
}
