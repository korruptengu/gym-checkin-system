package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.TrainerNotFoundException;
import com.korruptengu.gymcheckinsystem.repository.TrainerRepository;
import com.korruptengu.gymcheckinsystem.util.update.TrainerUpdateHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers(){
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(Long id){
        return trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
    }

    public Trainer createTrainer(Trainer trainer){
        if (trainer == null) throw new IllegalArgumentException("New data must not be null");
        return trainerRepository.save(trainer);
    }

    public Trainer deleteTrainer(Long id){
        Trainer deleted = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
        trainerRepository.delete(deleted);
        return deleted;
    }

    public Trainer updateTrainerCompletely(Long id, Trainer updateData){
        if (updateData == null) throw new IllegalArgumentException("Update data must not be null");
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
        TrainerUpdateHelper.updateCompletely(existingTrainer, updateData);
        return trainerRepository.save(existingTrainer);
    }

    public Trainer updateTrainerPartially(Long id, Trainer updateData){
        if (updateData == null) throw new IllegalArgumentException("Update data must not be null");
        if (TrainerUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
        TrainerUpdateHelper.updatePartially(existingTrainer, updateData);
        return trainerRepository.save(existingTrainer);
    }


}
