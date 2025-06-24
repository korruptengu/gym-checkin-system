package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Firstname is required")
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
        this(firstname, lastname, hireDate);
        this.specialty = specialty.trim().replaceAll("\\s{2,}", " ");
    }

    public Trainer(String firstname, String lastname, LocalDate hireDate, String specialty, TrainerState state){
        this(firstname, lastname, hireDate, specialty);
        this.state = state;
    }
}

