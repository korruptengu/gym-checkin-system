package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PatchCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PostCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.request.checkIn.PutCheckInRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CheckInResponse;

import java.util.List;

public interface CheckInService {
    List<CheckInResponse> getAllCheckIns();
    CheckInResponse getCheckInById(Long id);
    CheckInResponse createCheckIn(PostCheckInRequest request);
    CheckInResponse deleteCheckInById(Long id);
    CheckInResponse updateCheckInCompletely(Long id, PutCheckInRequest request);
    CheckInResponse updateCheckInPartially(Long id, PatchCheckInRequest request);
}
