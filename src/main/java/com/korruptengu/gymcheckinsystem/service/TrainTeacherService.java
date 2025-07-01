package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.trainTeacher.PostTrainTeacherRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainTeacherResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacherId;

import java.util.List;

public interface TrainTeacherService {
    List<TrainTeacherResponse> getAllTrainTeachers();
    TrainTeacherResponse getTrainTeacherById(TrainTeacherId id);
    TrainTeacherResponse createTrainTeacher(PostTrainTeacherRequest request);
    TrainTeacherResponse deleteTrainTeacherById(TrainTeacherId id);
}
