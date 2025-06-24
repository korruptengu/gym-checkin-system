package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.trainer.PatchTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PostTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PutTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainerResponse;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.mapper.TrainerMapper;
import com.korruptengu.gymcheckinsystem.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(TRAINERS)
public class TrainerController {
    private final TrainerService trainerService;
    private final TrainerMapper mapper;

    public TrainerController(TrainerService trainerService, TrainerMapper mapper) {
        this.trainerService = trainerService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getAllTrainers(){
        List<Trainer> allTrainer = trainerService.getAllTrainers();
        List<TrainerResponse> responseList = allTrainer.stream()
                .map(mapper :: toResponse)
                .toList();
        return ResponseEntity
                .ok(responseList);
    }

    @GetMapping(ID)
    public ResponseEntity<TrainerResponse> getTrainerById(@PathVariable Long id){
        Trainer trainer = trainerService.getTrainerById(id);
        return ResponseEntity
                .ok(mapper.toResponse(trainer));

    }

    @PostMapping
    public ResponseEntity<TrainerResponse> createTrainer(@Valid @RequestBody PostTrainerRequest request){
        Trainer created = trainerService.createTrainer(
                mapper.postRequestToEntity(request));
        URI location = URI.create(TRAINERS + "/" + created.getId());
        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(created));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Trainer> deleteTrainer(@PathVariable Long id){
        return ResponseEntity.ok(trainerService.deleteTrainer(id));
    }

    @PutMapping(ID)
    public ResponseEntity<TrainerResponse> updateTrainer(@PathVariable Long id, @Valid @RequestBody PutTrainerRequest request){
        Trainer updated = trainerService.updateTrainerCompletely(id, mapper.putRequestToEntity(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }

    @PatchMapping(ID)
    public ResponseEntity<TrainerResponse> partiallyUpdateTrainer(@PathVariable Long id, @RequestBody PatchTrainerRequest request){
        Trainer updated = trainerService.updateTrainerPartially(id, mapper.patchRequestToEntity(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }
}

