package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PatchTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PostTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PutTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainingSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingSessionMapper {
    TrainingSessionResponse toResponse(TrainingSession trainingSession);
    TrainingSession putRequestToEntity(PutTrainingSessionRequest request);
    TrainingSession postRequestToEntity(PostTrainingSessionRequest request);
    TrainingSession patchRequestToEntity(PatchTrainingSessionRequest request);

}
