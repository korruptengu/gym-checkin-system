package com.korruptengu.gymcheckinsystem.dto.request.checkIn;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchCheckInRequest(
        LocalDateTime checkInTime,
        LocalDateTime checkOutTime,
        Long memberId) {

}
