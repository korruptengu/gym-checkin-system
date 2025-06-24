package com.korruptengu.gymcheckinsystem.controller;

import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PatchTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PostTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.PutTrainingSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainingSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import com.korruptengu.gymcheckinsystem.mapper.TrainingSessionMapper;
import com.korruptengu.gymcheckinsystem.service.MemberService;
import com.korruptengu.gymcheckinsystem.service.TrainerService;
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
    private final TrainingSessionService trainingSessionService;
    private final MemberService memberService;
    private final TrainerService trainerService;
    private final TrainingSessionMapper mapper;

    @GetMapping
    public ResponseEntity<List<TrainingSessionResponse>> getAllTrainingSessions(){
        List<TrainingSession> allTrainingSessions = trainingSessionService.getAllTrainingSessions();
        List<TrainingSessionResponse> responseList = allTrainingSessions.stream()
                .map(mapper :: toResponse)
                .toList();
        return ResponseEntity
                .ok(responseList);
    }

    @GetMapping(ID)
    public ResponseEntity<TrainingSessionResponse> getTrainingSessionById(@PathVariable Long id){
        TrainingSession trainingSession = trainingSessionService.getTrainingSessionById(id);
        return ResponseEntity
                .ok(mapper.toResponse(trainingSession));
    }

    @PostMapping
    public ResponseEntity<TrainingSessionResponse> createTrainingSession(@Valid @RequestBody PostTrainingSessionRequest request){
        Member member = memberService.getMemberById(request.memberId());
        Trainer trainer = trainerService.getTrainerById(request.trainerId());
        TrainingSession created = trainingSessionService.createTrainingSession(
                new TrainingSession(request.startTime(), request.duration(), trainer, member));
        URI location = URI.create(TRAINING_SESSIONS + "/" + created.getId());
        return ResponseEntity
                .created(location).body(mapper.toResponse(created));
    }

    @DeleteMapping(ID)
    public ResponseEntity<TrainingSessionResponse> deleteTrainingSession(@PathVariable Long id){
        TrainingSession deleted = trainingSessionService.deleteTrainingSession(id);
        return ResponseEntity
                .ok(mapper.toResponse(deleted));
    }

    @PutMapping(ID)
    public ResponseEntity<TrainingSessionResponse> updateTrainingSession(@PathVariable Long id, @Valid @RequestBody PutTrainingSessionRequest request){
        TrainingSession updated = trainingSessionService
                .updateTrainingSessionCompletely(id, mapper.putRequestToEntity(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }

    // Kein @Valid – Felder im PatchRequest sind optional
    @PatchMapping(ID)
    public ResponseEntity<TrainingSessionResponse> partiallyUpdateTrainingSession(@PathVariable Long id, @RequestBody PatchTrainingSessionRequest request){
        TrainingSession updated = trainingSessionService
                .updateTrainingSessionPartially(id, mapper.patchRequestToEntity(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }

}
