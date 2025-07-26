package com.korruptengu.gymcheckinsystem.dto.request.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.enums.MemberState;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchMemberRequest(
        LocalDate joinDate,
        MemberState state
) {}
