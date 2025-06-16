package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
}
