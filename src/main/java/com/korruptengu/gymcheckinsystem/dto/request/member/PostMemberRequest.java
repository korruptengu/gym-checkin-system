package com.korruptengu.gymcheckinsystem.dto.request.member;

import com.korruptengu.gymcheckinsystem.enums.MemberState;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PostMemberRequest(
        @NotNull LocalDate joinDate,
        @NotNull MemberState state,
        @NotNull @Min(1) Long appUserId
) {}
