package com.korruptengu.gymcheckinsystem.dto.request.trainer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.enums.TrainerState;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchTrainerRequest(
        LocalDate hireDate,
        List<String> specialty,
        TrainerState state) {
}
