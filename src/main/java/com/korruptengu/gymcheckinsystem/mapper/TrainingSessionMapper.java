package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PatchTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PostTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PutTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainingSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainingSessionMapper {

    default TrainingSessionResponse toResponse(TrainingSession session) {
        if (session == null) return null;

        return new TrainingSessionResponse(
                session.getId(),
                session.getStartTime(),
                session.getDurationInMinutes(),
                session.getTrainer() != null ? session.getTrainer().getId() : null,
                session.getMember() != null ? session.getMember().getId() : null
        );
    }

    @Mapping(target = "id", ignore = true)
    TrainingSession putRequestToEntity(PutTrainingSessionRequest request);

    @Mapping(target = "id", ignore = true)
    TrainingSession postRequestToEntity(PostTrainingSessionRequest request);

    @Mapping(target = "id", ignore = true)
    TrainingSession patchRequestToEntity(PatchTrainingSessionRequest request);

}
