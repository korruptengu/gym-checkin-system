package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CourseBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "course_session_id", nullable = false)
    private CourseSession courseSession;

    public CourseBooking(Member member, CourseSession courseSession){
        if (member == null  || courseSession == null) throw new NullPointerException("Member and CourseSession must not be null");
        this.member = member;
        this.courseSession = courseSession;
    }

}
