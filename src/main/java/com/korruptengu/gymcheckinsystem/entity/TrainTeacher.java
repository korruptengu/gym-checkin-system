package com.korruptengu.gymcheckinsystem.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(TrainTeacherId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainTeacher {

    @Id
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Trainer instructor;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Trainer student;

}
