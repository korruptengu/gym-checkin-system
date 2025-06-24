package com.korruptengu.gymcheckinsystem.service;


import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.TrainingSessionNotFoundExeption;
import com.korruptengu.gymcheckinsystem.repository.TrainingSessionRepository;
import com.korruptengu.gymcheckinsystem.util.update.TrainingSessionUpdateHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;


    public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
        this.trainingSessionRepository = trainingSessionRepository;

    }

    public List<TrainingSession> getAllTrainingSessions(){
        return trainingSessionRepository.findAll();
    }

    public TrainingSession getTrainingSessionById(Long id){
        return trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundExeption(id));
    }

    public TrainingSession createTrainingSession(TrainingSession trainingSession){
        if (trainingSession == null) throw new IllegalArgumentException("New data must not be null");
        return trainingSessionRepository.save(trainingSession);
    }

    public TrainingSession deleteTrainingSession(Long id){
        TrainingSession deleted = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundExeption(id));
        trainingSessionRepository.delete(deleted);
        return deleted;
    }

    public TrainingSession updateTrainingSessionCompletely(Long id, TrainingSession updateData){
        if (updateData == null) throw new IllegalArgumentException("Update data must not be null");
        TrainingSession existing = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundExeption(id));
        TrainingSessionUpdateHelper.updateCompletely(existing, updateData);
        return trainingSessionRepository.save(existing);
    }

    public TrainingSession updateTrainingSessionPartially(Long id, TrainingSession updateData){
        if (updateData == null) throw new IllegalArgumentException("Update data must not be null");
        if (TrainingSessionUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        TrainingSession existing = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundExeption(id));
        TrainingSessionUpdateHelper.updatePartially(existing, updateData);
        return trainingSessionRepository.save(existing);
    }
}
