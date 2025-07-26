package com.korruptengu.gymcheckinsystem.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.korruptengu.gymcheckinsystem.enums.MemberState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate joinDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberState state = MemberState.ACTIVE;

    @OneToOne(optional = false)
    @JoinColumn(name = "app_user_id", unique = true)
    private AppUser appUser;
}
