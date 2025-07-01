package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PatchTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PostTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PutTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainingSessionResponse;

import java.util.List;

public interface TrainingSessionService {
    List<TrainingSessionResponse> getAllTrainingSessions();
    TrainingSessionResponse getTrainingSessionById(Long id);
    TrainingSessionResponse createTrainingSession(PostTrainingSessionRequest request);
    TrainingSessionResponse deleteTrainingSessionById(Long id);
    TrainingSessionResponse updateTrainingSessionCompletely(Long id, PutTrainingSessionRequest request);
    TrainingSessionResponse updateTrainingSessionPartially(Long id, PatchTrainingSessionRequest request);
}