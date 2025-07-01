package com.korruptengu.gymcheckinsystem.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckIn {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private LocalDateTime checkInTime;

     private LocalDateTime checkOutTime;

     @ManyToOne
     @JoinColumn(name = "member_id", nullable = false)
     private Member member;

     public CheckIn(LocalDateTime checkInTime, Member member){
         if (member == null) throw new NullPointerException("Member must not be null");
         this.checkInTime = checkInTime;
         this.member = member;
     }

     public int getCheckInDuration() {
         return checkOutTime.getMinute() - checkInTime.getMinute();
     }

}
