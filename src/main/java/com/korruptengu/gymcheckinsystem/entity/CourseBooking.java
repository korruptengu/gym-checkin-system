package com.korruptengu.gymcheckinsystem.entity;

import com.korruptengu.gymcheckinsystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseBooking {

    @EmbeddedId
    private CourseBookingId id;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @MapsId("courseSessionId")
    @JoinColumn(name = "course_session_id", nullable = false)
    private CourseSession courseSession;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private boolean attended = false;
}
