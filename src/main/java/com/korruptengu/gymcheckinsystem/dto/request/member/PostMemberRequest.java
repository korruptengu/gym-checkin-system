package com.korruptengu.gymcheckinsystem.dto.request.member;

import com.korruptengu.gymcheckinsystem.entity.MemberState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PostMemberRequest(
        @NotBlank String firstname,
        @NotBlank String lastname,
        @Email String eMail,
        MemberState state
) {}
