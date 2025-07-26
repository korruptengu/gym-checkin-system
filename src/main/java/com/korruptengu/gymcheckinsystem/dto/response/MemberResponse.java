package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.enums.MemberState;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        LocalDate joinDate,
        MemberState state,
        Long appUserId
) {}
