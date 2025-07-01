package com.korruptengu.gymcheckinsystem.dto.request.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.korruptengu.gymcheckinsystem.entity.MemberState;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchMemberRequest(
         String firstname,
         String lastname,
         String email,
         MemberState state) {
}
