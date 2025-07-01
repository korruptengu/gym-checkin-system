package com.korruptengu.gymcheckinsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ElementCollection
    @CollectionTable(name = "trainer_specialties", joinColumns = @JoinColumn(name = "trainer_id"))
    @Column(name = "specialty", nullable = true)
    private List<String> specialty = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false)
    private TrainerState state = TrainerState.NORMAL;


    // Konstruktor für Pflichtfelder ohne Spezialisierungen
    public Trainer(String firstname, String lastname, LocalDate hireDate){
        if (firstname == null || firstname.isBlank()) throw new IllegalArgumentException("Firstname must not be null or blank");
        if (lastname == null || lastname.isBlank()) throw new IllegalArgumentException("Lastname must not be null or blank");
        if (hireDate == null) throw new IllegalArgumentException("HireDate must not be null");
        this.firstname = firstname.trim().replaceAll("\\s{2,}", " ");
        this.lastname = lastname.trim().replaceAll("\\s{2,}", " ");
        this.hireDate = hireDate;
    }

    // Konstruktor inkl. Spezialisierungen
    public Trainer(String firstname, String lastname, LocalDate hireDate, List<String> specialtyList){
        this(firstname, lastname, hireDate);
        if (specialtyList != null){
            this.specialty.addAll(
                    specialtyList.stream()
                            .filter(Objects::nonNull)
                            .map(str -> str.trim().replaceAll("\\s{2,}"," " ))
                            .toList()
            );
        }
    }

    // Konstruktor inkl. Spezialisierungen und Zustand
    public Trainer(String firstname, String lastname, LocalDate hireDate, List<String> specialtyList, TrainerState state){
        this(firstname, lastname, hireDate, specialtyList);
        this.state = (state != null) ? state : TrainerState.NORMAL;
    }
}

