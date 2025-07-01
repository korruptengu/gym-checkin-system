package com.korruptengu.gymcheckinsystem.repository;

import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
}
