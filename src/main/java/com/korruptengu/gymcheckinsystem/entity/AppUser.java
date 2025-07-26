package com.korruptengu.gymcheckinsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korruptengu.gymcheckinsystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole uRole;

    // persönliche Daten
    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

}
