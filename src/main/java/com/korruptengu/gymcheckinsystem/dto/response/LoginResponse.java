package com.korruptengu.gymcheckinsystem.dto.response;

import com.korruptengu.gymcheckinsystem.enums.UserRole;
import org.antlr.v4.runtime.Token;

public record LoginResponse (
    String token,
    String username,
    UserRole uRole

){}
