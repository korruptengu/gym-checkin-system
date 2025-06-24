package com.korruptengu.gymcheckinsystem.dto.request.trainer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.entity.TrainerState;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchTrainerRequest(
        String firstname,
        String lastname,
        LocalDate hireDate,
        String specialty,
        TrainerState state) {
}
