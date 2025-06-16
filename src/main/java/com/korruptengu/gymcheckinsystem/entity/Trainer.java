package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = true)
    private String specialty;
    @Column(nullable = false)
    private LocalDate hireDate;
    @Column(nullable = false)
    private TrainerState state = TrainerState.NORMAL;

    public Trainer(String firstname, String lastname, LocalDate hireDate){
        if (firstname == null || firstname.isBlank()) throw new IllegalArgumentException("Firstname must not be null or blank");
        if (lastname == null || lastname.isBlank()) throw new IllegalArgumentException("Lastname must not be null or blank");
        if (hireDate == null) throw new IllegalArgumentException("HireDate must not be null");
        this.firstname = firstname.trim().replaceAll("\\s{2,}", " ");
        this.lastname = lastname.trim().replaceAll("\\s{2,}", " ");
        this.hireDate = hireDate;
        this.specialty = null;
    }

    public Trainer(String firstname, String lastname, LocalDate hireDate, String specialty){
        if (firstname == null || firstname.isBlank()) throw new IllegalArgumentException("Firstname must not be null or blank");
        if (lastname == null || lastname.isBlank()) throw new IllegalArgumentException("Lastname must not be null or blank");
        if (specialty == null || specialty.isBlank()) throw new IllegalArgumentException("Specialty must not be null or blank");
        if (hireDate == null) throw new IllegalArgumentException("HireDate must not be null");
        this.firstname = firstname.trim().replaceAll("\\s{2,}", " ");
        this.lastname = lastname.trim().replaceAll("\\s{2,}", " ");
        this.hireDate = hireDate;
        this.specialty = specialty.trim().replaceAll("\\s{2,}", " ");
    }
}

