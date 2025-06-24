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
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public TrainingSession(LocalDateTime startTime, Duration duration, Trainer trainer,  Member member){
        this.member = member;
        this.trainer = trainer;
        this.startTime = startTime;
        this.duration = duration;
    }

    public LocalDateTime getEndTime(){
        return startTime.plusMinutes((int) duration.toMinutes());
    }
}
