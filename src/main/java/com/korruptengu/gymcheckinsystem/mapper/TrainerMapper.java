package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.trainer.PatchTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PostTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PutTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainerResponse;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    TrainerResponse toResponse(Trainer trainer);

    @Mapping(target = "id", ignore = true)
    Trainer putRequestToEntity(PutTrainerRequest request);

    @Mapping(target = "id", ignore = true)
    Trainer postRequestToEntity(PostTrainerRequest request);

    @Mapping(target = "id", ignore = true)
    Trainer patchRequestToEntity(PatchTrainerRequest request);
}
