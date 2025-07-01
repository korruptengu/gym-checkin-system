package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.entity.TrainerState;

import java.time.LocalDate;
import java.util.List;

public record TrainerResponse(
        Long id,
        String firstname,
        String lastname,
        LocalDate hireDate,
        List<String> specialty,
        TrainerState state) {

}
