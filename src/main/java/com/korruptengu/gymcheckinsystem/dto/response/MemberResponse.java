package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.entity.MemberState;

public record MemberResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        MemberState state
) {}
