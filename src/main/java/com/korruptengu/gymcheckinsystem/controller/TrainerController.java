package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public List<Trainer> getAllTrainers(){
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public Trainer getTrainerById(@PathVariable Long id){
        return trainerService.getTrainerById(id);
    }

    @PostMapping
    public Trainer createTrainer(@RequestBody Trainer trainer){
        return trainerService.createTrainer(trainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Trainer> deleteTrainer(@PathVariable Long id){
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Trainer updateTrainer(@PathVariable Long id, @RequestBody Trainer updated){
        return trainerService.updateTrainer(id, updated);
    }
}

