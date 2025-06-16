package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
