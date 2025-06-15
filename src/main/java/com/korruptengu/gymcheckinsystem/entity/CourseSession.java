package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private int duration;

    @ManyToOne
    @JoinColumn(name = "course_type_id", nullable = false)
    private CourseType courseType;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    public CourseSession(LocalDateTime startTime, int duration, CourseType courseType,  Trainer trainer){
        if (courseType == null || trainer == null) throw new NullPointerException("CourseType and Trainer must not be null");
        this.courseType = courseType;
        this.trainer = trainer;
        this.startTime = startTime;
        this.duration = duration;
    }

    public LocalDateTime getEndTime(){
        return startTime.plusMinutes(duration);
    }
}
