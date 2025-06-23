package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.trainTeacher.PutTrainTeacherRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainTeacherResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacher;
import com.korruptengu.gymcheckinsystem.mapper.TrainTeacherMapper;
import com.korruptengu.gymcheckinsystem.service.TrainTeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(TRAIN_TEACHERS)
public class TrainTeacherController {
    private final TrainTeacherService trainTeacherService;

    public TrainTeacherController(TrainTeacherService trainTeacherService) {
        this.trainTeacherService = trainTeacherService;
    }

    @GetMapping
    public ResponseEntity<List<TrainTeacherResponse>> getAllTrainTeacher(){
        List<TrainTeacher> allTrainTeacher = trainTeacherService.getAllTrainTeachers();
        List<TrainTeacherResponse> responseList = allTrainTeacher.stream()
                .map(TrainTeacherMapper :: toResponse)
                .toList();
        return  ResponseEntity.ok(responseList);
    }

    @GetMapping(TRAIN_TEACHER_ID)
    public ResponseEntity<TrainTeacherResponse> getTrainTeacherById(@PathVariable Long instructorId, @PathVariable Long studentId){
        TrainTeacher trainTeacher = trainTeacherService.getTrainTeacherById(instructorId, studentId);
        return ResponseEntity
                .ok(TrainTeacherMapper.toResponse(trainTeacher));
    }

    @PostMapping
    public ResponseEntity<TrainTeacherResponse>  createTrainTeacher(@RequestBody PutTrainTeacherRequest request){
        TrainTeacher createdTrainTeacher = trainTeacherService.createTrainTeacher(request.instructorId(), request.studentId());
        URI location = URI.create(TRAIN_TEACHERS + "/"
                + createdTrainTeacher.getInstructor().getId() + "/"
                + createdTrainTeacher.getStudent().getId());
        return ResponseEntity
                .created(location)
                .body(TrainTeacherMapper.toResponse(createdTrainTeacher));
    }

    @DeleteMapping(TRAIN_TEACHER_ID)
    public ResponseEntity<TrainTeacherResponse> deleteTrainTeacher(@PathVariable Long instructorId, @PathVariable Long studentId){
        TrainTeacher deletedTrainTeacher = trainTeacherService.deleteTrainTeacher(instructorId, studentId);
        return ResponseEntity
                .ok(TrainTeacherMapper.toResponse(deletedTrainTeacher));
    }
}
