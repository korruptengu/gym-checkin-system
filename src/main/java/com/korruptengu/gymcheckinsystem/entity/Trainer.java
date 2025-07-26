package com.korruptengu.gymcheckinsystem.entity;

import com.korruptengu.gymcheckinsystem.enums.TrainerState;
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

    @ElementCollection
    @CollectionTable(name = "trainer_specialties", joinColumns = @JoinColumn(name = "trainer_id"))
    @Column(name = "specialty", nullable = true)
    private List<String> specialty = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false)
    private TrainerState state = TrainerState.NORMAL;

    @OneToOne(optional = false)
    @JoinColumn(name = "app_user_id", unique = true)
    private AppUser appUser;

    // Konstruktor für Pflichtfelder ohne Spezialisierungen
    public Trainer(LocalDate hireDate){
        if (hireDate == null) throw new IllegalArgumentException("HireDate must not be null");
        this.hireDate = hireDate;
    }

    // Konstruktor inkl. Spezialisierungen
    public Trainer(LocalDate hireDate, List<String> specialtyList){
        this(hireDate);
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
        this(hireDate, specialtyList);
        this.state = (state != null) ? state : TrainerState.NORMAL;
    }
}

