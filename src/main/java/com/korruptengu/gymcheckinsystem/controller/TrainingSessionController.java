package com.korruptengu.gymcheckinsystem.controller;

import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PatchTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PostTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PutTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainingSessionResponse;
import com.korruptengu.gymcheckinsystem.service.TrainingSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(TRAINING_SESSIONS)
@RequiredArgsConstructor
public class TrainingSessionController {
    private final TrainingSessionService service;

    @GetMapping
    public ResponseEntity<List<TrainingSessionResponse>> getAllTrainingSessions(){
        List<TrainingSessionResponse> allTrainingSessions = service.getAllTrainingSessions();
        return ResponseEntity
                .ok(allTrainingSessions);
    }

    @GetMapping(ID)
    public ResponseEntity<TrainingSessionResponse> getTrainingSessionById(@PathVariable Long id){
        TrainingSessionResponse trainingSession = service.getTrainingSessionById(id);
        return ResponseEntity
                .ok(trainingSession);
    }

    @PostMapping
    public ResponseEntity<TrainingSessionResponse> createTrainingSession(@Valid @RequestBody PostTrainingSessionRequest request){

        TrainingSessionResponse created = service.createTrainingSession(request);
        URI location = URI.create(TRAINING_SESSIONS + "/" + created.id());
        return ResponseEntity
                .created(location).body(created);
    }

    @DeleteMapping(ID)
    public ResponseEntity<TrainingSessionResponse> deleteTrainingSession(@PathVariable Long id){
        TrainingSessionResponse deleted = service.deleteTrainingSessionById(id);
        return ResponseEntity
                .ok(deleted);
    }

    @PutMapping(ID)
    public ResponseEntity<TrainingSessionResponse> updateTrainingSession(@PathVariable Long id, @Valid @RequestBody PutTrainingSessionRequest request){
        TrainingSessionResponse updated = service
                .updateTrainingSessionCompletely(id, request);
        return ResponseEntity
                .ok(updated);
    }

    // Kein @Valid – Felder im PatchRequest sind optional
    @PatchMapping(ID)
    public ResponseEntity<TrainingSessionResponse> partiallyUpdateTrainingSession(@PathVariable Long id, @RequestBody PatchTrainingSessionRequest request){
        TrainingSessionResponse updated = service
                .updateTrainingSessionPartially(id, request);
        return ResponseEntity
                .ok(updated);
    }

}
