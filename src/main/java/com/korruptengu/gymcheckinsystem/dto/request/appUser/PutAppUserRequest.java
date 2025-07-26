package com.korruptengu.gymcheckinsystem.dto.request.appUser;

import com.korruptengu.gymcheckinsystem.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PutAppUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull UserRole uRole,
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotBlank String email,
        Long memberId,
        Long trainerId
) {}

