package com.korruptengu.gymcheckinsystem.dto.request.trainer;

import com.korruptengu.gymcheckinsystem.entity.TrainerState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record PostTrainerRequest(
    @NotBlank String firstname,
    @NotBlank String lastname,
    @NotNull LocalDate hireDate,
    @NotBlank List<String> specialty,
    TrainerState state) {
}
