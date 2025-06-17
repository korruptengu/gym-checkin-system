package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.repository.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException("Trainer with Id: " + id + " not found"));
    }

    public Trainer createTrainer(Trainer trainer){
        if (trainer == null) throw new IllegalArgumentException("New data must not be null");
        return trainerRepository.save(trainer);
    }

    public Trainer deleteTrainer(Long id){
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with Id: " + id + " not found"));
        trainerRepository.delete(trainer);
        return trainer;
    }

    public Trainer updateTrainer(Long id, Trainer trainerUpdate){
        if (trainerUpdate == null) throw new IllegalArgumentException("Update data must not be null");
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with Id: " + id + " not found"));
        if (trainerUpdate.getFirstname() != null) existingTrainer.setFirstname(trainerUpdate.getFirstname());
        if (trainerUpdate.getLastname() != null) existingTrainer.setLastname(trainerUpdate.getLastname());
        if (trainerUpdate.getState() != null) existingTrainer.setState(trainerUpdate.getState());
        if (trainerUpdate.getSpecialty() != null) existingTrainer.setSpecialty(trainerUpdate.getSpecialty());
        if (trainerUpdate.getHireDate() != null) existingTrainer.setHireDate(trainerUpdate.getHireDate());
        return trainerRepository.save(existingTrainer);
    }


}
