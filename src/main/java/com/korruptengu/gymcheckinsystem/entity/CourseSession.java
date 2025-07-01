package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CourseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "course_type_id", nullable = false)
    private CourseType courseType;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    public CourseSession(LocalDateTime startTime, Integer durationInMinutes, CourseType courseType, Trainer trainer){
        this.courseType = courseType;
        this.trainer = trainer;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }


    public LocalDateTime getEndTime(){
        return startTime.plusMinutes(this.durationInMinutes);
    }
}
