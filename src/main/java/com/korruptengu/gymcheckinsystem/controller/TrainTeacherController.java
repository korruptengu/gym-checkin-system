package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.trainTeacher.PostTrainTeacherRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainTeacherResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacherId;
import com.korruptengu.gymcheckinsystem.service.TrainTeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(TRAIN_TEACHERS)
@RequiredArgsConstructor
public class TrainTeacherController {
    private final TrainTeacherService service;

    @GetMapping
    public ResponseEntity<List<TrainTeacherResponse>> getAllTrainTeachers() {
        List<TrainTeacherResponse> allResponses = service.getAllTrainTeachers();
        return ResponseEntity.ok(allResponses);
    }

    @GetMapping(TRAIN_TEACHER_COMPOSITE_ID)
    public ResponseEntity<TrainTeacherResponse> getTrainTeacherById(
            @PathVariable Long instructorId,
            @PathVariable Long studentId) {

        TrainTeacherId id = toId(instructorId, studentId);
        TrainTeacherResponse response = service.getTrainTeacherById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TrainTeacherResponse> createTrainTeacher(
            @Valid @RequestBody PostTrainTeacherRequest request) {

        TrainTeacherResponse response = service.createTrainTeacher(request);
        URI location = URI.create(TRAIN_TEACHERS + "/" + response.instructorId() + "/" + response.studentId());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping(TRAIN_TEACHER_COMPOSITE_ID)
    public ResponseEntity<TrainTeacherResponse> deleteTrainTeacher(
            @PathVariable Long instructorId,
            @PathVariable Long studentId) {

        TrainTeacherId id = toId(instructorId, studentId);
        TrainTeacherResponse response = service.deleteTrainTeacherById(id);
        return ResponseEntity.ok(response);
    }

    private TrainTeacherId toId(Long instructorId, Long studentId) {
        return new TrainTeacherId(instructorId, studentId);
    }
}
