package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.entity.TrainTeacher;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacherId;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.exception.DuplicateTrainTeacherException;
import com.korruptengu.gymcheckinsystem.exception.TrainTeacherNotFoundException;
import com.korruptengu.gymcheckinsystem.exception.TrainerNotFoundException;
import com.korruptengu.gymcheckinsystem.repository.TrainTeacherRepository;
import com.korruptengu.gymcheckinsystem.repository.TrainerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainTeacherService {
    private final TrainTeacherRepository trainTeacherRepository;
    private final TrainerRepository trainerRepository;

    public TrainTeacherService(TrainTeacherRepository trainTeacherRepository, TrainerRepository trainerRepository) {
        this.trainTeacherRepository = trainTeacherRepository;
        this.trainerRepository = trainerRepository;
    }

    public List<TrainTeacher> getAllTrainTeachers(){
        return trainTeacherRepository.findAll();
    }

    public TrainTeacher getTrainTeacherById(Long instructorId, Long studentId){
        TrainTeacherId trainTeacherId = new TrainTeacherId(instructorId, studentId);
        return trainTeacherRepository.findById(trainTeacherId)
                .orElseThrow(() -> new TrainTeacherNotFoundException(instructorId, studentId));
    }

    public TrainTeacher createTrainTeacher(TrainTeacher trainTeacher){
        if(trainTeacher == null) throw new IllegalArgumentException("New data must not be null");
        return trainTeacherRepository.save(trainTeacher);
    }

    public TrainTeacher createTrainTeacher(Long instructorId, Long studentId){
        if (instructorId == null || studentId == null) throw new IllegalArgumentException("Instructor Id and Student Id must not be null");

        Trainer instructor = trainerRepository.findById(instructorId)
                .orElseThrow(() -> new TrainerNotFoundException(instructorId));
        Trainer student = trainerRepository.findById(studentId)
                .orElseThrow(() -> new TrainerNotFoundException(studentId));

        TrainTeacherId trainTeacherId = new TrainTeacherId(instructorId, studentId);
        if (trainTeacherRepository.existsById(trainTeacherId)) throw new DuplicateTrainTeacherException(instructorId, studentId);

        return trainTeacherRepository.save(new TrainTeacher(instructor, student));
    }

    public TrainTeacher deleteTrainTeacher(Long instructorId, Long studentId){
        TrainTeacherId trainTeacherId = new TrainTeacherId(instructorId, studentId);
        TrainTeacher deletedTrainTeacher = trainTeacherRepository.findById(trainTeacherId)
                .orElseThrow(() -> new TrainTeacherNotFoundException(instructorId, studentId));
        trainTeacherRepository.delete(deletedTrainTeacher);
        return deletedTrainTeacher;
    }
}