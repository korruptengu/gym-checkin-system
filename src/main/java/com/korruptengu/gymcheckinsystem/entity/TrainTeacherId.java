package com.korruptengu.gymcheckinsystem.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TrainTeacherId implements Serializable {
    private Long instructor;
    private Long student;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof TrainTeacherId other)) return false;
        return Objects.equals(this.instructor, other.instructor) && Objects.equals(this.student, other.student);
    }

    @Override
    public int hashCode(){
        return Objects.hash(instructor, student);
    }
}
