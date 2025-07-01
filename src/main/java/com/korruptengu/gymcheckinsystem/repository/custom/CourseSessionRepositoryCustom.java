package com.korruptengu.gymcheckinsystem.repository.custom;

import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.entity.Trainer;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseSessionRepositoryCustom {
    List<CourseSession> findConflictingSessions(Trainer trainer, LocalDateTime start, LocalDateTime end);
}
