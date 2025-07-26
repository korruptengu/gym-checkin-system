package com.korruptengu.gymcheckinsystem.dto.request.appUser;
import com.korruptengu.gymcheckinsystem.enums.UserRole;

public record PatchAppUserRequest(
        String password,
        UserRole uRole,
        String firstname,
        String lastname,
        String email,
        Long memberId,
        Long trainerId
) {}

