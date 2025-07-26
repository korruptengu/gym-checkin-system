package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.enums.TrainerState;

import java.time.LocalDate;
import java.util.List;

public record TrainerResponse(
        Long id,
        LocalDate hireDate,
        List<String> specialty,
        TrainerState state,
        Long appUserId) {

}
