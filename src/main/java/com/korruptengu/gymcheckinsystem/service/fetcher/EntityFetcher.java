package com.korruptengu.gymcheckinsystem.service.fetcher;

import com.korruptengu.gymcheckinsystem.entity.*;
import com.korruptengu.gymcheckinsystem.exception.*;
import com.korruptengu.gymcheckinsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityFetcher {

    private final AppUserRepository appUserRepository;
    private final CheckInRepository checkInRepository;
    private final CourseSessionRepository courseSessionRepository;
    private final CourseBookingRepository courseBookingRepository;
    private final CourseTypeRepository courseTypeRepository;
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingSessionRepository trainingSessionRepository;
    private final TrainTeacherRepository trainTeacherRepository;


    public AppUser fetchAppUser(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));
    }

    public CheckIn fetchCheckIn(Long id) {
        return checkInRepository.findById(id)
                .orElseThrow(() -> new CheckInNotFoundException(id));
    }

    public CourseSession fetchCourseSession(Long id) {
        return courseSessionRepository.findById(id)
                .orElseThrow(() -> new CourseSessionNotFoundException(id));
    }

    public CourseBooking fetchCourseBooking(CourseBookingId id) {
        return courseBookingRepository.findById(id)
                .orElseThrow(() -> new CourseBookingNotFoundException(id.getMemberId(), id.getCourseSessionId()));
    }

    public CourseType fetchCourseType(Long id) {
        return courseTypeRepository.findById(id)
                .orElseThrow(() -> new CourseTypeNotFoundException(id));
    }

    public Member fetchMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public Trainer fetchTrainer(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
    }

    public TrainingSession fetchTrainingSession(Long id) {
        return trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundException(id));
    }

    public TrainTeacher fetchTrainTeacher(TrainTeacherId id) {
        return trainTeacherRepository.findById(id)
                .orElseThrow(() -> new TrainTeacherNotFoundException(id.getInstructor(), id.getStudent()));
    }
}