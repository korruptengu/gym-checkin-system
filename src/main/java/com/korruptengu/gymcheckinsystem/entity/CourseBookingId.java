package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Embeddable
@Getter
public class CourseBookingId implements Serializable {
    private Long memberId;
    private Long courseSessionId;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof CourseBookingId other)) return false;
        return Objects.equals(this.memberId, other.memberId) && Objects.equals(this.courseSessionId, other.courseSessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId,courseSessionId);
    }
}
