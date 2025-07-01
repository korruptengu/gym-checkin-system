package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.trainer.PatchTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PostTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PutTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainerResponse;
import com.korruptengu.gymcheckinsystem.entity.Trainer;

import java.util.List;

public interface TrainerService {
    List<TrainerResponse> getAllTrainers();
    TrainerResponse getTrainerById(Long id);
    TrainerResponse createTrainer(PostTrainerRequest request);
    TrainerResponse deleteTrainerById(Long id);
    TrainerResponse updateTrainerCompletely(Long id, PutTrainerRequest request);
    TrainerResponse updateTrainerPartially(Long id, PatchTrainerRequest request);
}
