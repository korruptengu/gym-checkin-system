package com.korruptengu.gymcheckinsystem.dto.request.trainer;

import com.korruptengu.gymcheckinsystem.enums.TrainerState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record PutTrainerRequest(
        @NotNull LocalDate hireDate,
        @NotBlank List<String> specialty,
        @NotNull TrainerState state){

}
