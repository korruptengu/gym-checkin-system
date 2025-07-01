package com.korruptengu.gymcheckinsystem.repository.custom.impl;

import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.repository.custom.CourseSessionRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

public class CourseSessionRepositoryImpl implements CourseSessionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CourseSession> findConflictingSessions(Trainer trainer, LocalDateTime start, LocalDateTime end){
        String jpql = "SELECT cs FROM CourseSession cs " +
                "WHERE cs.trainer.id = :trainerId " +
                "AND cs.startTime < :end ";

        List<CourseSession> sessionList = em
                .createQuery(jpql, CourseSession.class)
                .setParameter("trainerId", trainer.getId())
                .setParameter("end", end)
                .getResultList();

        return sessionList.stream()
                .filter(cs -> cs.getEndTime().isAfter(start))
                .filter(cs -> cs.getStartTime().isBefore(end))
                .toList();
    }
}
