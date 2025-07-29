package com.korruptengu.gymcheckinsystem.dto.response;

public record AppUserResponse (
        Long id,
        String username,
        String role,
        String firstname,
        String lastname,
        String email,
        Long memberId,
        Long trainerId){
}
