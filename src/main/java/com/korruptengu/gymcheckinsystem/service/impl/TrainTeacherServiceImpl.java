package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.trainTeacher.PostTrainTeacherRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainTeacherResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacher;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacherId;
import com.korruptengu.gymcheckinsystem.mapper.TrainTeacherMapper;
import com.korruptengu.gymcheckinsystem.repository.TrainTeacherRepository;

import com.korruptengu.gymcheckinsystem.service.TrainTeacherService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainTeacherServiceImpl implements TrainTeacherService {
    private final TrainTeacherRepository repository;
    private final TrainTeacherMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<TrainTeacherResponse> getAllTrainTeachers() {
        List<TrainTeacher> allTrainTeachers = repository.findAll();
        List<TrainTeacherResponse> responseList = new ArrayList<>();
        for (TrainTeacher allTrainTeacher : allTrainTeachers) {
            responseList.add(mapper.toResponse(allTrainTeacher));
        }
        return responseList;
    }

    @Override
    public TrainTeacherResponse getTrainTeacherById(TrainTeacherId id) {
        TrainTeacher assignment = fetcher.fetchTrainTeacherById(id);
        return mapper.toResponse(assignment);
    }

    @Override
    public TrainTeacherResponse createTrainTeacher(PostTrainTeacherRequest request) {
        if (request == null) throw new IllegalArgumentException("New data must not be null");

        TrainTeacher created = mapper.postRequestToEntity(request);
        created.setInstructor(fetcher.fetchTrainerById(request.instructorId()));
        created.setStudent(fetcher.fetchTrainerById(request.studentId()));

        repository.save(created);
        return mapper.toResponse(created);
    }

    @Override
    public TrainTeacherResponse deleteTrainTeacherById(TrainTeacherId id) {
        TrainTeacher deleted = fetcher.fetchTrainTeacherById(id);
        repository.delete(deleted);
        return mapper.toResponse(deleted);
    }
}