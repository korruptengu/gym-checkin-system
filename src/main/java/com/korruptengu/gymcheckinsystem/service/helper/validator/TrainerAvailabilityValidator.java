package com.korruptengu.gymcheckinsystem.service.helper.validator;

import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.repository.CourseSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrainerAvailabilityValidator {

    private final CourseSessionRepository courseSessionRepository;

    public boolean isTrainerAlreadyBooked(CourseSession session) {
        List<CourseSession> conflicts = courseSessionRepository
                .findConflictingSessions(
                        session.getTrainer(),
                        session.getStartTime(),
                        session.getEndTime());

        return !conflicts.isEmpty();
    }

    public boolean isTrainerAlreadyBookedExceptSelf(CourseSession session, Long selfId) {
        List<CourseSession> conflicts = courseSessionRepository
                .findConflictingSessions(session.getTrainer(),
                        session.getStartTime(),
                        session.getEndTime());

        return conflicts.stream().anyMatch(cs -> !cs.getId().equals(selfId));
    }
}
