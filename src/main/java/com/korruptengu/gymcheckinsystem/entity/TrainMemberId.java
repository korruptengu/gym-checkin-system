package com.korruptengu.gymcheckinsystem.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TrainMemberId implements Serializable {
    private Long trainer;
    private Long member;

    public TrainMemberId(Long trainer, Long member){
        this.trainer = trainer;
        this.member = member;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof TrainMemberId other)) return false;
        return Objects.equals(this.trainer, other.trainer) && Objects.equals(this.member, other.member);
    }

    @Override
    public int hashCode(){
        return Objects.hash(trainer, member);
    }
}