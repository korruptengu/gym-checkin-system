package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.entity.TrainerState;

import java.time.LocalDate;

public record TrainerResponse(
        Long id,
        String firstname,
        String lastname,
        LocalDate hireDate,
        String specialty,
        TrainerState state) {

}
