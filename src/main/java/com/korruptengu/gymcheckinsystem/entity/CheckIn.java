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
     private LocalDateTime timestamp;

     @ManyToOne
     @JoinColumn(name = "member_id", nullable = false)
     private Member member;

     public CheckIn(LocalDateTime timestamp, Member member){
         if (member == null) throw new NullPointerException("Member must not be null");
         this.timestamp = timestamp;
         this.member = member;
     }

}
