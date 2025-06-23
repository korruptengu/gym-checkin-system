package com.korruptengu.gymcheckinsystem.dto.response;

public record MemberResponse(
        Long id,
        String firstname,
        String lastname,
        String eMail
) {}
