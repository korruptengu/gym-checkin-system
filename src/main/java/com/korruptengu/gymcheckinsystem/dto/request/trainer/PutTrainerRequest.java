package com.korruptengu.gymcheckinsystem.dto.request.trainer;

import com.korruptengu.gymcheckinsystem.entity.TrainerState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PutTrainerRequest(
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotNull LocalDate hireDate,
        @NotBlank String specialty,
        @NotNull TrainerState state){

}
