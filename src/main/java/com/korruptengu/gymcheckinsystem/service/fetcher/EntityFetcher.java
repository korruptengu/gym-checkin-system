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

    // ========== Fetcher by Id ========== //

    public AppUser fetchAppUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException(id));
    }

    public CheckIn fetchCheckInById(Long id) {
        return checkInRepository.findById(id)
                .orElseThrow(() -> new CheckInNotFoundException(id));
    }

    public CourseSession fetchCourseSessionById(Long id) {
        return courseSessionRepository.findById(id)
                .orElseThrow(() -> new CourseSessionNotFoundException(id));
    }

    public CourseBooking fetchCourseBookingById(CourseBookingId id) {
        return courseBookingRepository.findById(id)
                .orElseThrow(() -> new CourseBookingNotFoundException(id.getMemberId(), id.getCourseSessionId()));
    }

    public CourseType fetchCourseTypeById(Long id) {
        return courseTypeRepository.findById(id)
                .orElseThrow(() -> new CourseTypeNotFoundException(id));
    }

    public Member fetchMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public Trainer fetchTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
    }

    public TrainingSession fetchTrainingSessionById(Long id) {
        return trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundException(id));
    }

    public TrainTeacher fetchTrainTeacherById(TrainTeacherId id) {
        return trainTeacherRepository.findById(id)
                .orElseThrow(() -> new TrainTeacherNotFoundException(id.getInstructor(), id.getStudent()));
    }

    // ========== Fetcher by name ========== //

    public AppUser fetchAppUserByUsername(String username){
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new AppUserNotFoundException(username));
    }
}