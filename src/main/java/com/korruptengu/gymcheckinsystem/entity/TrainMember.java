package com.korruptengu.gymcheckinsystem.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(TrainMemberId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainMember {

    @Id
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}
