package com.korruptengu.gymcheckinsystem.dto.response;

public record LoginResponse (
    String token,
    String username,
    String role

){}
