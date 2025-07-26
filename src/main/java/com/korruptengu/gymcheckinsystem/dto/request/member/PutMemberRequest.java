package com.korruptengu.gymcheckinsystem.dto.request.member;

import com.korruptengu.gymcheckinsystem.enums.MemberState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PutMemberRequest(
        @NotNull LocalDate joinDate,
        @NotNull MemberState state
) {}
