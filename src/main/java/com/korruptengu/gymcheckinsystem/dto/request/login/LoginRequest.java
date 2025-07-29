package com.korruptengu.gymcheckinsystem.dto.request.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
     @NotBlank String username,
     @NotBlank String password
) {}
