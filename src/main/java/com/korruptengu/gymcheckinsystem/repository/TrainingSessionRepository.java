package com.korruptengu.gymcheckinsystem.repository;

import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
}
