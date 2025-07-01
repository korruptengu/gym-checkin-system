package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public TrainingSession(LocalDateTime startTime, Integer durationInMinutes, Trainer trainer,  Member member){
        this.member = member;
        this.trainer = trainer;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }

    public LocalDateTime getEndTime(){return startTime.plusMinutes((int) + durationInMinutes);
    }
}
