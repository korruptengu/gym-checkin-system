package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacher;
import com.korruptengu.gymcheckinsystem.entity.TrainTeacherId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainTeacherRepository extends JpaRepository<TrainTeacher, TrainTeacherId>{
}
