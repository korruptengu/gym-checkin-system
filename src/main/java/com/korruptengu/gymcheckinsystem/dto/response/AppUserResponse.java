package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.enums.UserRole;

public record AppUserResponse (
        Long id,
        String username,
        UserRole uRole,
        String firstname,
        String lastname,
        String email,
        Long memberId,
        Long trainerId
    ){}
