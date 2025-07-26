package com.korruptengu.gymcheckinsystem.dto.request.appUser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PostAppUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull UserRole uRole,
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotBlank String email,
        Long memberId,
        Long trainerId
    ){}