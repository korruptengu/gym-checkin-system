package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.trainer.PatchTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PostTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PutTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainerResponse;
import com.korruptengu.gymcheckinsystem.service.TrainerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(TRAINERS)
@AllArgsConstructor
public class TrainerController {
    private final TrainerService service;

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getAllTrainers(){
        List<TrainerResponse> allTrainers = service.getAllTrainers();
        return ResponseEntity
                .ok(allTrainers);
    }

    @GetMapping(ID)
    public ResponseEntity<TrainerResponse> getTrainerById(@PathVariable Long id){
        TrainerResponse trainer = service.getTrainerById(id);
        return ResponseEntity
                .ok(trainer);

    }

    @PostMapping
    public ResponseEntity<TrainerResponse> createTrainer(@Valid @RequestBody PostTrainerRequest request){
        TrainerResponse created = service.createTrainer(request);
        URI location = URI.create(TRAINERS + "/" + created.id());
        return ResponseEntity
                .created(location)
                .body(created);
    }

    @DeleteMapping(ID)
    public ResponseEntity<TrainerResponse> deleteTrainer(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteTrainerById(id));
    }

    @PutMapping(ID)
    public ResponseEntity<TrainerResponse> updateTrainer(@PathVariable Long id, @Valid @RequestBody PutTrainerRequest request){
        TrainerResponse updated = service.updateTrainerCompletely(id, request);
        return ResponseEntity
                .ok(updated);
    }

    @PatchMapping(ID)
    public ResponseEntity<TrainerResponse> partiallyUpdateTrainer(@PathVariable Long id, @RequestBody PatchTrainerRequest request){
        TrainerResponse updated = service.updateTrainerPartially(id, request);
        return ResponseEntity
                .ok(updated);
    }
}

